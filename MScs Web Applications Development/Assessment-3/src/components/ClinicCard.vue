<template>
  <div class="d-md-none">
    <div class="card mb-3" v-for="clinic in clinics" :key="clinic.id">
      <div class="card-body">
        <!-- Non-Editable Card View -->
        <template v-if="editingRowId !== clinic.id">
          <h5 class="card-title">{{ clinic.name }}</h5>
          <p class="card-text">
            <strong>Address:</strong> {{ clinic.streetNumber }} {{ clinic.streetName }},
            {{ clinic.suburb }}, {{ clinic.state }}, {{ clinic.postcode }}
          </p>
          <p class="card-text">
            <!-- Convert number to String and append the missing first 0  -->
            <strong>Phone:</strong> {{ clinic.phone.toString().padStart(10, '0') }}
          </p>
          <p class="card-text"><strong>Hours:</strong> {{ clinic.hours }}</p>
          <div class="d-flex justify-content-end">
            <button class="btn btn-warning btn-sm me-2" @click="$emit('edit', clinic)">
              <i class="bi bi-pencil"></i>
            </button>
            <button class="btn btn-danger btn-sm" @click="$emit('delete', clinic.id)">
              <i class="bi bi-trash3"></i>
            </button>
          </div>
        </template>

        <!-- Editable Card View -->
        <template v-else>
          <div class="floating-label">
            <input
              type="text"
              id="name-mobile-{{ clinic.id }}"
              class="form-control mb-2"
              :class="{ 'is-invalid': errors.name }"
              v-model="clinic.name"
              placeholder=" "
              @blur="() => validateField('name', clinic.name, errors, true)"
              @input="() => validateField('name', clinic.name, errors, false)"
            />
            <label for="name-mobile-{{ clinic.id }}">Clinic Name</label>
            <small v-if="errors.name" class="text-danger">
              {{ errors.name }}
            </small>
          </div>

          <div class="floating-label">
            <input
              type="text"
              id="streetNumber-mobile-{{ clinic.id }}"
              class="form-control mb-2"
              v-model.number="clinic.streetNumber"
              placeholder=" "
            />
            <label for="streetNumber-mobile-{{ clinic.id }}">Street Number</label>
          </div>

          <div class="floating-label">
            <input
              type="text"
              id="streetName-mobile-{{ clinic.id }}"
              class="form-control mb-2"
              v-model="clinic.streetName"
              placeholder=" "
            />
            <label for="streetName-mobile-{{ clinic.id }}">Street Name</label>
          </div>

          <div class="floating-label">
            <input
              type="text"
              id="suburb-mobile-{{ clinic.id }}"
              class="form-control mb-2"
              v-model="clinic.suburb"
              placeholder=" "
            />
            <label for="suburb-mobile-{{ clinic.id }}">Suburb</label>
          </div>

          <div class="floating-label">
            <input
              type="text"
              id="state-mobile-{{ clinic.id }}"
              class="form-control mb-2"
              v-model="clinic.state"
              placeholder=" "
            />
            <label for="state-mobile-{{ clinic.id }}">State</label>
          </div>

          <div class="floating-label">
            <input
              type="text"
              id="postcode-mobile-{{ clinic.id }}"
              class="form-control mb-2"
              :class="{ 'is-invalid': errors.postcode }"
              v-model.number="clinic.postcode"
              placeholder=" "
              @blur="() => validateField('postcode', clinic.postcode, errors, true)"
              @input="() => validateField('postcode', clinic.postcode, errors, false)"
            />
            <label for="postcode-mobile-{{ clinic.id }}">Postcode</label>
            <small v-if="errors.postcode" class="text-danger">
              {{ errors.postcode }}
            </small>
          </div>

          <div class="floating-label">
            <input
              type="tel"
              pattern="[0-9]{10}"
              id="phone-mobile-{{ clinic.id }}"
              class="form-control mb-2"
              :class="{ 'is-invalid': errors.phone }"
              v-model.number="clinic.phone"
              placeholder=" "
              @blur="() => validateField('phone', clinic.phone, errors, true)"
              @input="() => validateField('phone', clinic.phone, errors, false)"
            />
            <label for="phone-mobile-{{ clinic.id }}">Phone</label>
            <small v-if="errors.phone" class="text-danger">
              {{ errors.phone }}
            </small>
          </div>

          <div class="floating-label">
            <input
              type="text"
              id="hours-mobile-{{ clinic.id }}"
              class="form-control mb-2"
              v-model="clinic.hours"
              placeholder=" "
            />
            <label for="hours-mobile-{{ clinic.id }}">Opening Hours</label>
          </div>

          <div class="d-flex justify-content-end">
            <button class="btn btn-success btn-sm me-2" @click="$emit('saveEdit', clinic)">
              Save
            </button>
            <button class="btn btn-secondary btn-sm" @click="$emit('cancelEdit', clinic)">
              Cancel
            </button>
          </div>
        </template>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { validateName, validatePostcode, validatePhone } from '@/helpers/validation.js'

defineProps(['clinics', 'editingRowId'])
defineEmits(['edit', 'delete', 'saveEdit', 'cancelEdit'])

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
</script>

<style scoped>
.card {
  border: 1px solid #ddd;
  border-radius: 8px;
  padding: 1rem;
  margin-bottom: 1rem;
}

.card-title {
  font-size: 1.25rem;
  font-weight: bold;
}

.card-text {
  margin-bottom: 0.5rem;
}

.card .d-flex {
  justify-content: flex-end;
}

.card input {
  margin-bottom: 0.5rem;
}

.floating-label {
  position: relative;
  margin-bottom: 1rem;
}

.floating-label input {
  width: 100%;
  padding: 0.75rem 0.75rem 0.25rem;
  font-size: 1rem;
  border: 1px solid #ced4da;
  border-radius: 4px;
  box-sizing: border-box;
}

.floating-label label {
  position: absolute;
  top: 50%;
  left: 0.75rem;
  transform: translateY(-50%);
  font-size: 1rem;
  color: #6c757d;
  pointer-events: none;
  transition: all 0.2s ease-in-out;
}

.floating-label input:focus + label,
.floating-label input:not(:placeholder-shown) + label {
  top: 0.25rem;
  font-size: 0.75rem;
  color: #495057;
}

.floating-label input:focus {
  outline: none;
  border-color: #80bdff;
  box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
}
</style>
