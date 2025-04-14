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
        <td>{{ clinic.name }}</td>
        <td>{{ clinic.streetNumber }}, {{ clinic.streetName }}</td>
        <td>{{ clinic.suburb }}</td>
        <td>{{ clinic.state }}</td>
        <td>{{ clinic.postcode }}</td>
        <!-- Convert number to String and append the missing first 0  -->
        <td>{{ clinic.phone.toString().padStart(10, '0') }}</td>
        <td>{{ clinic.hours }}</td>
        <td>
          <button class="btn btn-warning btn-sm me-2" @click="startEdit(clinic.id)">
            <i class="material-icons">edit</i>
          </button>
        </td>
        <td>
          <button class="btn btn-danger btn-sm" @click="deleteclinic(clinic.id)">
            <i class="material-icons">delete</i>
          </button>
        </td>
      </tr>
    </tbody>
  </table>
</template>

<script setup>
defineProps(['clinics'])
defineEmits(['edit', 'delete'])
</script>
