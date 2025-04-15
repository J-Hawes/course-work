<template>
  <div class="container">
    <h1>Administration Dashboard</h1>
    <p>Welcome to the admin dashboard!</p>

    <!-- Cards Section -->
    <div class="row mb-4">
      <!-- User Count Card -->
      <div class="col-md-3 col-6">
        <div class="card text-center">
          <div class="card-body">
            <h5 class="card-title">Users</h5>
            <p class="card-text">{{ userCount }}</p>
            <button class="btn btn-primary me-2" @click="userAdmin">User Admin</button>
          </div>
        </div>
      </div>

      <!-- Health Clinics Card -->
      <div class="col-md-3 col-6">
        <div class="card text-center">
          <div class="card-body">
            <h5 class="card-title">Health Clinics</h5>
            <p class="card-text">{{ healthClinicCount }}</p>
            <button
              class="btn btn-primary me-2"
              :class="{ active: selectedCollection === 'healthClinics' }"
              @click="loadHealthClinics"
            >
              Edit Health Clinics
            </button>
          </div>
        </div>
      </div>

      <!-- Mental Health Clinics Card -->
      <div class="col-md-3 col-6">
        <div class="card text-center">
          <div class="card-body">
            <h5 class="card-title">Mental Health Clinics</h5>
            <p class="card-text">{{ mentalHealthClinicCount }}</p>
            <button
              class="btn btn-primary"
              :class="{ active: selectedCollection === 'mentalHealthClinics' }"
              @click="loadMentalHealthClinics"
            >
              Edit Mental Health Clinics
            </button>
          </div>
        </div>
      </div>

      <!-- New Messages Card -->
      <div class="col-md-3 col-6">
        <div class="card text-center">
          <div class="card-body">
            <h5 class="card-title">New Messages</h5>
            <p class="card-text">{{ newMessagesCount }}</p>
            <button
              class="btn btn-primary"
              :class="{ active: selectedCollection === 'messages' }"
              @click="loadMessages"
            >
              View Messages
            </button>
          </div>
        </div>
      </div>
    </div>

    <div class="content">
      <!-- Dynamically load the selected options -->
      <div v-if="selectedCollection === 'healthClinics'">
        <AdminClinicTable collectionName="healthClinics" title="Health Clinics" />
      </div>
      <div v-else-if="selectedCollection === 'mentalHealthClinics'">
        <AdminClinicTable collectionName="mentalHealthClinics" title="Mental Health Clinics" />
      </div>
      <div v-else-if="selectedCollection === 'messages'">
        <MessagesTable title="Messages" />
      </div>
      <div v-else></div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { collection, getDocs } from 'firebase/firestore'
import db from '@/firebase/init.js'
import AdminClinicTable from '@/components/AdminClinicTable.vue'
import MessagesTable from '@/components/MessagesTable.vue'

// Reactive variables for card data
const userCount = ref(0)
const healthClinicCount = ref(0)
const mentalHealthClinicCount = ref(0)
const newMessagesCount = ref(0)

// Reactive variable to track the selected collection
const selectedCollection = ref(null)

// Functions to switch between lists
const loadHealthClinics = () => {
  selectedCollection.value = 'healthClinics'
}

const loadMentalHealthClinics = () => {
  selectedCollection.value = 'mentalHealthClinics'
}

const loadMessages = () => {
  selectedCollection.value = 'messages'
}

// Fetch dashboard data to populate the cards
const fetchDashboardData = async () => {
  try {
    // Fetch user count
    const usersSnapshot = await getDocs(collection(db, 'users'))
    userCount.value = usersSnapshot.size

    // Fetch health clinics count
    const healthClinicsSnapshot = await getDocs(collection(db, 'healthClinics'))
    healthClinicCount.value = healthClinicsSnapshot.size

    // Fetch mental health clinics count
    const mentalHealthClinicsSnapshot = await getDocs(collection(db, 'mentalHealthClinics'))
    mentalHealthClinicCount.value = mentalHealthClinicsSnapshot.size

    // Fetch new messages count
    const messagesSnapshot = await getDocs(collection(db, 'contact'))
    newMessagesCount.value = messagesSnapshot.size
  } catch (error) {
    console.error('Error fetching dashboard data:', error)
  }
}

const userAdmin = () => {
  alert('User admin functionality is not implemented yet.')
}

// Fetch data when the component is mounted
onMounted(() => {
  fetchDashboardData()
})
</script>

<style scoped>
/* Adjust card size and spacing for smaller screens */
@media (max-width: 576px) {
  .card {
    font-size: 0.9rem;
    padding: 0.5rem;
  }

  .card .card-title {
    font-size: 1rem;
  }

  .card .card-text {
    font-size: 0.8rem;
  }
  .card .btn {
    font-size: 0.8rem;
    padding: 0.3rem 0.5rem;
  }
}
</style>
