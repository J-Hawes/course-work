<template>
  <div class="d-flex flex-wrap justify-content-start" v-for="clinic in clinics" :key="clinic.id">
    <div class="card mb-3 w-100 w-sm-50">
      <div class="row g-0">
        <div class="col-md-2 d-flex align-items-center justify-content-center">
          <iframe
            class="img-fluid rounded-start responsive-img"
            width="100%"
            :src="`https://maps.google.com/maps?width=100%25&height=330&hl=en&q=
                  ${encodeURIComponent(`${clinic.streetNumber} ${clinic.streetName}, ${clinic.suburb}, ${clinic.state} ${clinic.postcode}`)}
                  &t=&z=15&ie=UTF8&iwloc=B&output=embed`"
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
              <li><strong>Phone: </strong> 0{{ clinic.phone }}</li>
              <li><strong>Hours: </strong>{{ clinic.hours }}</li>
            </ul>
          </div>
        </div>
        <div class="col-md-2 d-flex align-items-center justify-content-center">
          <div>
            <button class="btn btn-primary w-100" v-if="isAuthenticated">Make a booking</button>
            <RouterLink class="btn btn-primary w-100" to="/login" v-else>
              Log in to make a booking
            </RouterLink>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { isAuthenticated, updateAuthStatus } from '../stores/auth'
import { ref, onMounted, watch } from 'vue'
import { useRoute } from 'vue-router'
import db from '../firebase/init.js'
import { collection, getDocs, orderBy, query } from 'firebase/firestore'

const clinics = ref([])
const route = useRoute()

const fetchClinics = async (collectionName) => {
  try {
    const q = query(collection(db, collectionName), orderBy('name'))
    const querySnapshot = await getDocs(q)
    const clinicsArray = []
    querySnapshot.forEach((doc) => {
      clinicsArray.push({ id: doc.id, ...doc.data() })
    })
    clinics.value = clinicsArray
  } catch (error) {
    console.error('Error fetching clinics:', error)
  }
}

watch(
  () => route.query.services,
  (newCollection) => {
    if (newCollection) {
      fetchClinics(newCollection)
    }
  },
  { immediate: true },
)

onMounted(() => {
  updateAuthStatus()
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
