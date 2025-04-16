<template>
  <div class="container mt-5">
    <div class="text-center">
      <h1>Administration Dashboard</h1>
      <p>Welcome to the admin dashboard!</p>
    </div>
    <!-- Cards Section -->
    <div class="row mb-4">
      <!-- User Count Card -->
      <div class="col-md-3 col-6">
        <div class="card text-center">
          <div class="card-body">
            <h5 class="card-title">Registered Users</h5>
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
              class="btn btn-primary me-2"
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
              class="btn btn-primary me-2"
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
        <MessagesTable collectionName="contactFormMessages" title="Messages" />
      </div>
      <div v-else></div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { collection, onSnapshot } from 'firebase/firestore'
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
const fetchDashboardData = () => {
  try {
    // Fetch user count
    onSnapshot(collection(db, 'users'), (snapshot) => {
      userCount.value = snapshot.size
    })

    // Fetch health clinics count
    onSnapshot(collection(db, 'healthClinics'), (snapshot) => {
      healthClinicCount.value = snapshot.size
    })

    // Fetch mental health clinics count
    onSnapshot(collection(db, 'mentalHealthClinics'), (snapshot) => {
      mentalHealthClinicCount.value = snapshot.size
    })

    // Fetch new messages count
    onSnapshot(collection(db, 'contactFormMessages'), (snapshot) => {
      newMessagesCount.value = snapshot.size
    })
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
    height: 7rem;
    font-size: 0.6rem;
    overflow-y: auto;
  }

  .card .card-body {
    padding: 0.1rem;
  }

  .card .card-title {
    white-space: nowrap;
    font-size: 1rem;
    overflow: hidden;
  }

  .card .card-text {
    font-size: 0.8rem;
  }
  .card .btn {
    font-size: 0.8rem;
    padding: 0.1rem 0.2rem;
  }
}
</style>
