<template>
  <div class="row justify-content-center">
    <div class="col-md-12">
      <h1 class="text-center mt-4">Clinic List</h1>
      <div class="d-flex justify-content-center mb-3">
        <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addClinicModal">
          Add New Clinic
        </button>
      </div>
      <ClinicTable v-if="isDesktop" :clinics="clinics" @edit="startEdit" @delete="deleteclinic" />
      <ClinicCard v-else :clinics="clinics" @edit="startEdit" @delete="deleteclinic" />
      <AddClinicModal :newClinic="newClinic" @add="addClinic" @clear="clearNewClinic" />
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, onUnmounted } from 'vue'
import db from '../firebase/init.js'
import ClinicTable from './ClinicTable.vue'
import ClinicCard from './ClinicCard.vue'
import AddClinicModal from './AddClinicModal.vue'
import {
  collection,
  getDocs,
  deleteDoc,
  addDoc,
  doc,
  query,
  orderBy,
  limit,
} from 'firebase/firestore'

const clinics = ref([])
const editingclinic = ref(null)
const editingRowId = ref(null)

const newClinic = ref({
  name: '',
  streetNumber: '',
  streetName: '',
  suburb: '',
  state: '',
  postcode: '',
  phone: '',
  hours: '',
})

// Check if the screen size is desktop or mobile
const isDesktop = ref(window.innerWidth >= 768)

// Update the isDesktop value based on the screen size
const updateIsDesktop = () => {
  isDesktop.value = window.innerWidth >= 768
}

// Add event listener to update isDesktop when the window is resized
onMounted(() => {
  window.addEventListener('resize', updateIsDesktop)
  fetchclinics()
})

// Remove the event listener when the component is unmounted
onUnmounted(() => {
  window.removeEventListener('resize', updateIsDesktop)
})

// Fetches clinics from the Firestore database based on the provided collection name.
const fetchclinics = async () => {
  try {
    // ToDo fix this to change clinics param to query from linked page
    const q = query(collection(db, 'healthClinics'), orderBy('name'), limit(10))
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

const addClinic = async () => {
  try {
    if (!newClinic.value.name || !newClinic.value.streetName) {
      alert('Please fill in all required fields.')
      return
    }

    const clinicRef = await addDoc(collection(db, 'healthClinics'), newClinic.value)
    clinics.value.push({ id: clinicRef.id, ...newClinic.value })

    alert('Clinic added successfully!')
    clearNewClinic()
  } catch (error) {
    console.error('Error adding clinic:', error)
  }
}

// Delete the clinic from the database and remove it from the clinics array
const deleteclinic = async (id) => {
  try {
    const confirmDelete = window.confirm('Are you sure you want to remove this clinic?')
    if (!confirmDelete) {
      return
    }
    await deleteDoc(doc(db, 'clinics', id))
    clinics.value = clinics.value.filter((clinic) => clinic.id !== id)
    alert('clinic deleted successfully!')
  } catch (error) {
    console.error('Error deleting clinic:', error)
  }
}

const startEdit = (clinic) => {
  editingclinic.value = { ...clinic }
  editingRowId.value = clinic.id
}

const clearNewClinic = () => {
  newClinic.value = {
    name: '',
    streetNumber: '',
    streetName: '',
    suburb: '',
    state: '',
    postcode: '',
    phone: '',
    hours: '',
  }
}

onMounted(fetchclinics)
</script>
