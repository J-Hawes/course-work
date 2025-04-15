<template>
  <table class="table table-striped d-none d-md-table">
    <thead>
      <tr>
        <th>Name</th>
        <th>Street Address</th>
        <th>Suburb</th>
        <th>State</th>
        <th>Postcode</th>
        <th>Phone</th>
        <th>Opening Hours</th>
        <th>Edit</th>
        <th>Delete</th>
      </tr>
    </thead>
    <tbody>
      <!-- Existing Clinics Rows -->
      <tr v-for="clinic in clinics" :key="clinic.id">
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
defineProps(['clinics', 'editingRowId'])
defineEmits(['edit', 'delete', 'saveEdit', 'cancelEdit'])
</script>

<style scoped>
.table input {
  width: 100%;
  padding: 0.5rem;
  font-size: 0.9rem;
}
</style>
