<template>
  <div>
    <!-- Search Input -->
    <div class="input-group mb-3">
      <input
        type="text"
        class="form-control"
        placeholder="Search clinics..."
        v-model="searchQuery"
      />
      <span class="input-group-text">
        <i class="bi bi-search"></i>
      </span>
    </div>

    <!-- Display clinic as cards -->
    <div
      class="d-flex flex-wrap justify-content-start"
      v-for="clinic in filteredClinics"
      :key="clinic.id"
    >
      <div class="card mb-3 w-100 w-sm-50">
        <div class="row g-0">
          <div class="col-md-2 d-flex align-items-center justify-content-center">
            <!-- Generate google map iframe based on the clinic data retrieved from Firestore using an API-free version -->
            <iframe
              class="img-fluid rounded-start responsive-img"
              width="100%"
              referrerpolicy="no-referrer-when-downgrade"
              :src="`https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3151.
          835434509374!2d144.9630579153169!3d-37.81627974202198!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.
          1!3m3!1m2!1s0x6ad642af0f11fd81%3A0xf577d8b8c8b8b8b8!2s
          ${encodeURIComponent(`${clinic.streetNumber} ${clinic.streetName}, ${clinic.suburb}, ${clinic.state} ${clinic.postcode}`)}!
          5e0!3m2!1sen!2sau!4v1610000000000!5m2!1sen!2sau`"
            >
            </iframe>
          </div>
          <div class="col-md-8">
            <div class="card-body">
              <h4 class="card-title text-center">{{ clinic.name }}</h4>
              <ul class="card-text">
                <li>
                  <strong>Address: </strong>
                  {{ clinic.streetNumber }} {{ clinic.streetName }}, {{ clinic.suburb }},
                  {{ clinic.state }}, {{ clinic.postcode }}
                </li>
                <li><strong>Phone: </strong> {{ clinic.phone.toString().padStart(10, '0') }}</li>
                <li><strong>Hours: </strong>{{ clinic.hours }}</li>
              </ul>
            </div>
          </div>
          <div class="col-md-2 d-flex align-items-center justify-content-center">
            <div>
              <!---- Button changes to 'make a booking' or 'log in' depending on users authentication status -->
              <button class="btn btn-primary w-100" v-if="isAuthenticated" @click="makeBooking">
                Make a booking
              </button>
              <RouterLink class="btn btn-primary w-100" to="/login" v-else>
                Log in to make a booking
              </RouterLink>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useAuthStore } from '@/stores/auth.js'
import { ref, onMounted, watch, computed, markRaw, shallowRef } from 'vue'
import { useRoute } from 'vue-router'
import db from '../firebase/init.js'
import { collection, getDocs, orderBy, query } from 'firebase/firestore'

const clinics = shallowRef([])
const searchQuery = ref('')
const route = useRoute()
const authStore = useAuthStore()
const isAuthenticated = computed(() => authStore.isAuthenticated)

// Fetch clinics from the Firestore database based on the provided collection name.
const fetchClinics = async (collectionName) => {
  try {
    const q = query(collection(db, collectionName), orderBy('name'))
    const querySnapshot = await getDocs(q)
    const clinicsArray = []
    querySnapshot.forEach((doc) => {
      clinicsArray.push(markRaw({ id: doc.id, ...doc.data() }))
    })
    clinics.value = clinicsArray
  } catch (error) {
    console.error('Error fetching clinics:', error)
  }
}

// Computed property to filter clinics based on the search query
const filteredClinics = computed(() => {
  if (!searchQuery.value) return clinics.value
  const query = searchQuery.value.toLowerCase()
  return clinics.value.filter(
    (clinic) =>
      clinic.name.toLowerCase().includes(query) ||
      clinic.streetName.toLowerCase().includes(query) ||
      clinic.suburb.toLowerCase().includes(query) ||
      clinic.state.toLowerCase().includes(query) ||
      clinic.postcode.toString().includes(query) ||
      clinic.phone.toString().includes(query) ||
      clinic.hours.toLowerCase().includes(query),
  )
})

const makeBooking = () => {
  alert('Booking functionality is not implemented yet.')
}

// Watches for changes in the route query parameters and fetches clinics accordingly.
watch(
  () => route.query.services,
  (newCollection) => {
    if (newCollection) {
      fetchClinics(newCollection)
    }
  },
  { immediate: true },
)

// Watches for changes in the authentication status and updates the clinics list accordingly.
// This is used for changing the button from 'log in' to 'make a booking'.
onMounted(() => {
  if (route.query.services) {
    fetchClinics(route.query.services)
  }
})
</script>

<style scoped>
.responsive-img {
  width: 100%;
}

@media (max-width: 576px) {
  .responsive-img {
    width: 50%;
  }
}
</style>
