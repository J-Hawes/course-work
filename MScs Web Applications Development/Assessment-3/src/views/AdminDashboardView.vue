<template>
  <div class="container">
    <h1>Administration Dashboard</h1>
    <p>Welcome to the admin dashboard!</p>
    <div class="content">
      <!-- Buttons to switch between lists -->
      <div class="d-flex justify-content-center mb-4">
        <button
          class="btn btn-primary me-2"
          :class="{ active: selectedCollection === 'healthClinics' }"
          @click="loadHealthClinics"
        >
          Edit Health Clinics
        </button>
        <button
          class="btn btn-primary"
          :class="{ active: selectedCollection === 'mentalHealthClinics' }"
          @click="loadMentalHealthClinics"
        >
          Edit Mental Health Clinics
        </button>
      </div>

      <!-- Dynamically load the selected list -->
      <div>
        <AdminClinicList
          v-if="selectedCollection === 'healthClinics'"
          collectionName="healthClinics"
          title="Health Clinics"
        />
        <AdminClinicList
          v-else-if="selectedCollection === 'mentalHealthClinics'"
          collectionName="mentalHealthClinics"
          title="Mental Health Clinics"
        />
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import AdminClinicList from '@/components/AdminClinicList.vue'

// Reactive variable to track the selected collection
const selectedCollection = ref('healthClinics')

// Functions to switch between lists
const loadHealthClinics = () => {
  selectedCollection.value = 'healthClinics'
}

const loadMentalHealthClinics = () => {
  selectedCollection.value = 'mentalHealthClinics'
}
</script>

<style scoped></style>
