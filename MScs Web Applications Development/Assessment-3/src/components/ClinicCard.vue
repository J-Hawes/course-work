<template>
  <div class="d-md-none">
    <div class="card mb-3" v-for="clinic in clinics" :key="clinic.id">
      <div class="card-body">
        <div class="card-body">
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
            <button class="btn btn-warning btn-sm me-2" @click="startEdit(clinic)">
              <i class="material-icons">edit</i>
            </button>
            <button class="btn btn-danger btn-sm" @click="deleteclinic(clinic.id)">
              <i class="material-icons">delete</i>
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
defineProps(['clinics'])
defineEmits(['edit', 'delete'])
</script>
