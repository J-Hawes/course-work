<template>
  <table class="table table-striped d-none d-md-table">
    <thead>
      <tr>
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
      <tr v-for="clinic in sortedClinics" :key="clinic.id">
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
              <i class="material-icons">edit</i>
            </button>
          </td>
          <td>
            <button class="btn btn-danger btn-sm" @click="$emit('delete', clinic.id)">
              <i class="material-icons">delete</i>
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
              v-model="clinic.name"
            />
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
              v-model.number="clinic.postcode"
            />
          </td>
          <td>
            <input
              type="tel"
              pattern="[0-9]{10}"
              id="phone{{ clinic.id }}"
              class="form-control"
              v-model.number="clinic.phone"
            />
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
            <button class="btn btn-secondary btn-sm" @click="$emit('cancelEdit', clinic)">
              Cancel
            </button>
          </td>
        </template>
      </tr>
    </tbody>
  </table>
</template>

<script setup>
import { ref, computed } from 'vue'
// Destructure props
const { clinics, editingRowId } = defineProps(['clinics', 'editingRowId'])

defineEmits(['edit', 'delete', 'saveEdit', 'cancelEdit'])

// State for sorting
const sortKey = ref('name') // Default sort column
const sortOrder = ref('asc') // Default sort order ('asc' or 'desc')

// Function to sort the table
const sortTable = (key) => {
  if (sortKey.value === key) {
    // Toggle sort order if the same column is clicked
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

// Function to get the sort indicator (e.g., ↑ or ↓)
const getSortIndicator = (key) => {
  if (sortKey.value === key) {
    return sortOrder.value === 'asc' ? '↑' : '↓'
  }
  return ''
}
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
</style>
