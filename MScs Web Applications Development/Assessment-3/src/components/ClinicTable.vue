<template>
  <table class="table table-striped d-none d-md-table">
    <thead>
      <tr>
        <!-- Sortable Table Headers -->
        <th @click="sortTable('name')">
          Name <span class="sort-icon">{{ getSortIndicator('name') }}</span>
        </th>
        <th @click="sortTable('streetName')">
          Street Address <span class="sort-icon">{{ getSortIndicator('streetName') }}</span>
        </th>
        <th @click="sortTable('suburb')">
          Suburb <span class="sort-icon">{{ getSortIndicator('suburb') }}</span>
        </th>
        <th @click="sortTable('state')">
          State <span class="sort-icon">{{ getSortIndicator('state') }}</span>
        </th>
        <th @click="sortTable('postcode')">
          Postcode <span class="sort-icon">{{ getSortIndicator('postcode') }}</span>
        </th>
        <th @click="sortTable('phone')">
          Phone <span class="sort-icon">{{ getSortIndicator('phone') }}</span>
        </th>
        <th @click="sortTable('hours')">
          Opening Hours <span class="sort-icon">{{ getSortIndicator('hours') }}</span>
        </th>
        <th>Edit</th>
        <th>Delete</th>
      </tr>
    </thead>
    <tbody>
      <!-- Existing Clinics Rows -->
      <tr v-for="clinic in paginatedClinics" :key="clinic.id">
        <template v-if="editingRowId !== clinic.id">
          <td>{{ clinic.name }}</td>
          <td>{{ clinic.streetNumber }}, {{ clinic.streetName }}</td>
          <td>{{ clinic.suburb }}</td>
          <td>{{ clinic.state }}</td>
          <td>{{ clinic.postcode }}</td>
          <!-- Convert number to String and append the missing first 0  -->
          <td>{{ clinic.phone.toString().padStart(10, '0') }}</td>
          <td>{{ clinic.hours }}</td>
          <td>
            <button class="btn btn-warning btn-sm me-2" @click="$emit('edit', clinic)">
              <i class="bi bi-pencil"></i>
            </button>
          </td>
          <td>
            <button class="btn btn-danger btn-sm" @click="$emit('delete', clinic.id)">
              <i class="bi bi-trash3"></i>
            </button>
          </td>
        </template>

        <!-- Editable Row -->
        <template v-else>
          <td>
            <input
              type="text"
              id="name{{ clinic.id }}"
              class="form-control"
              :class="{ 'is-invalid': errors.name }"
              v-model="clinic.name"
              @blur="() => validateField('name', clinic.name, errors, true)"
              @input="() => validateField('name', clinic.name, errors, false)"
            />
            <small v-if="errors.name" class="text-danger">
              {{ errors.name }}
            </small>
          </td>
          <td>
            <div class="row">
              <div class="col-4">
                <input
                  type="text"
                  id="streetNumber{{ clinic.id }}"
                  class="form-control"
                  v-model.number="clinic.streetNumber"
                />
              </div>
              <div class="col-8">
                <input
                  type="text"
                  id="streetName{{ clinic.id }}"
                  class="form-control"
                  v-model="clinic.streetName"
                />
              </div>
            </div>
          </td>
          <td>
            <input
              type="text"
              id="suburb{{ clinic.id }}"
              class="form-control"
              v-model="clinic.suburb"
            />
          </td>
          <td>
            <input
              type="text"
              id="state{{ clinic.id }}"
              class="form-control"
              v-model="clinic.state"
            />
          </td>
          <td>
            <input
              type="text"
              id="postcode{{ clinic.id }}"
              class="form-control"
              :class="{ 'is-invalid': errors.postcode }"
              v-model.number="clinic.postcode"
              @blur="() => validateField('postcode', clinic.postcode, errors, true)"
              @input="() => validateField('postcode', clinic.postcode, errors, false)"
            />
            <small v-if="errors.postcode" class="text-danger">
              {{ errors.postcode }}
            </small>
          </td>
          <td>
            <input
              type="tel"
              pattern="[0-9]{10}"
              id="phone{{ clinic.id }}"
              class="form-control"
              :class="{ 'is-invalid': errors.phone }"
              v-model="clinic.phone"
              @blur="() => validateField('phone', clinic.phone, errors, true)"
              @input="() => validateField('phone', clinic.phone, errors, false)"
            />
            <small v-if="errors.phone" class="text-danger">
              {{ errors.phone }}
            </small>
          </td>
          <td>
            <input
              type="text"
              id="hours{{ clinic.id }}"
              class="form-control"
              v-model="clinic.hours"
            />
          </td>
          <td>
            <button class="btn btn-success btn-sm me-2" @click="$emit('saveEdit', clinic)">
              Save
            </button>
          </td>
          <td>
            <button
              class="btn btn-secondary btn-sm"
              @click="
                () => {
                  resetErrors()
                  $emit('cancelEdit', clinic)
                }
              "
            >
              Cancel
            </button>
          </td>
        </template>
      </tr>
    </tbody>
  </table>

  <!-- Pagination Controls -->
  <div class="my-3">
    <div class="pagination-controls mb-5">
      <button
        class="btn btn-secondary btn-sm"
        :disabled="currentPage === 1"
        @click="changePage(currentPage - 1)"
      >
        Previous
      </button>
      <span>Page {{ currentPage }} of {{ totalPages }}</span>
      <button
        class="btn btn-secondary btn-sm"
        :disabled="currentPage === totalPages"
        @click="changePage(currentPage + 1)"
      >
        Next
      </button>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import { validateName, validatePostcode, validatePhone } from '@/helpers/validation.js'
// Destructure props
const { clinics, editingRowId } = defineProps(['clinics', 'editingRowId'])

defineEmits(['edit', 'delete', 'saveEdit', 'cancelEdit', 'reset-errors'])

const errors = ref({
  name: null,
  streetNumber: null,
  streetName: null,
  suburb: null,
  state: null,
  postcode: null,
  phone: null,
  hours: null,
})

const resetErrors = () => {
  errors.value = {
    name: null,
    streetNumber: null,
    streetName: null,
    suburb: null,
    state: null,
    postcode: null,
    phone: null,
    hours: null,
  }
}

// Function to validate some of the fields
// Without integrated maps api to validate a correct address
// Only the name, postcode and phone are validated for values and correct patterns
const validateField = (field, value, error, blur) => {
  if (!value || value.toString().trim() === '') {
    errors.value[field] = `${field} is required.`
  } else if (blur) {
    switch (field) {
      case 'name':
        validateName(value, field, error, blur)
        break
      case 'postcode':
        validatePostcode(value, error, blur)
        break
      case 'phone':
        validatePhone(value, error, blur)
        break
      default:
        errors.value[field] = null
        break
    }
  } else {
    errors.value[field] = null
  }
}

// State for sorting
const sortKey = ref('name') // Default sort column
const sortOrder = ref('asc') // Default sort order ('asc' or 'desc')

// Pagination state
const currentPage = ref(1) // Current page
const rowsPerPage = ref(5) // Rows per page

// Function to sort the table
const sortTable = (key) => {
  if (sortKey.value === key) {
    // Toggle sort order when the column is clicked
    sortOrder.value = sortOrder.value === 'asc' ? 'desc' : 'asc'
  } else {
    // Set new sort key and default to ascending order
    sortKey.value = key
    sortOrder.value = 'asc'
  }
}

// Computed property to return sorted clinics
const sortedClinics = computed(() => {
  return [...clinics].sort((a, b) => {
    const valueA = a[sortKey.value]
    const valueB = b[sortKey.value]

    if (valueA < valueB) return sortOrder.value === 'asc' ? -1 : 1
    if (valueA > valueB) return sortOrder.value === 'asc' ? 1 : -1
    return 0
  })
})

// Function to get the sort arrow indicator depending on the current sort key and order
const getSortIndicator = (key) => {
  if (sortKey.value === key) {
    return sortOrder.value === 'asc' ? '↑' : '↓'
  }
  return ''
}

// Computed property to return paginated results
const paginatedClinics = computed(() => {
  const start = (currentPage.value - 1) * rowsPerPage.value
  const end = start + rowsPerPage.value
  return sortedClinics.value.slice(start, end)
})

// Function to change the page
const changePage = (page) => {
  currentPage.value = page
}

const totalPages = computed(() => {
  return Math.ceil(sortedClinics.value.length / rowsPerPage.value)
})
</script>

<style scoped>
.table input {
  width: 100%;
  padding: 0.5rem;
  font-size: 0.9rem;
}
th {
  cursor: pointer;
  user-select: none;
}

.sort-icon {
  margin-left: 0.5rem;
  font-size: 0.8rem;
  color: gray;
}

.pagination-controls {
  margin-top: 1rem;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 1rem;
}
.btn-secondary {
  background-color: hsla(160, 100%, 37%, 0.5);
  border-color: #6c757d;
}
</style>
