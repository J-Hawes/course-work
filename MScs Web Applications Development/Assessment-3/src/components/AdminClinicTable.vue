<template>
  <div class="row justify-content-center">
    <div class="col-md-12">
      <h1 class="text-center mt-4">{{ title }}</h1>
      <div class="d-flex justify-content-end mb-3">
        <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addClinicModal">
          Add New Clinic
        </button>
      </div>
      <ClinicTable
        v-if="isDesktop"
        :clinics="clinics"
        :editing-row-id="editingRowId"
        @edit="startEdit"
        @delete="deleteClinic"
        @saveEdit="saveEdit"
        @cancelEdit="cancelEdit"
      />
      <ClinicCard
        v-else
        :clinics="clinics"
        :editing-row-id="editingRowId"
        @edit="startEdit"
        @delete="deleteClinic"
        @saveEdit="saveEdit"
        @cancelEdit="cancelEdit"
      />
      <AddClinicModal :newClinic="newClinic" @add="addClinic" @clear="clearNewClinic" />
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, onUnmounted, watchEffect } from 'vue'
import db from '../firebase/init.js'
import ClinicTable from './ClinicTable.vue'
import ClinicCard from './ClinicCard.vue'
import AddClinicModal from './AddClinicModal.vue'
import {
  collection,
  getDocs,
  deleteDoc,
  addDoc,
  updateDoc,
  doc,
  query,
  orderBy,
  limit,
} from 'firebase/firestore'

const { collectionName, title } = defineProps({
  collectionName: String,
  title: String,
})

const clinics = ref([])
// Copy the clinic data for editing
const editingclinic = ref(null)
// Set the editing row ID
const editingRowId = ref(null)
// Set the new clinic data
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

// Check if the screen size is desktop or mobile and update the isDesktop value accordingly
const isDesktop = ref(window.innerWidth >= 768)
const updateIsDesktop = () => {
  isDesktop.value = window.innerWidth >= 768
}

// Add event listener to update isDesktop when the window is resized
// and fetch clinics when the component is mounted
onMounted(() => {
  window.addEventListener('resize', updateIsDesktop)
  fetchclinics()
})

// Remove the event listener when the component is unmounted
onUnmounted(() => {
  window.removeEventListener('resize', updateIsDesktop)
})

watchEffect(async () => {
  if (collectionName) {
    try {
      const q = query(collection(db, collectionName), orderBy('name'), limit(10))
      const querySnapshot = await getDocs(q)
      clinics.value = querySnapshot.docs.map((doc) => ({ id: doc.id, ...doc.data() }))
    } catch (error) {
      console.error(`Error fetching clinics from ${collectionName}:`, error)
    }
  }
})

// Fetches clinics from the Firestore database based on the provided collection name.
const fetchclinics = async () => {
  try {
    // ToDo fix this to change clinics param to query from linked page
    const q = query(collection(db, collectionName), orderBy('name'), limit(10))
    // Get the documents from the collection and map them to the clinics array
    const querySnapshot = await getDocs(q)
    clinics.value = querySnapshot.docs.map((doc) => ({ id: doc.id, ...doc.data() }))
  } catch (error) {
    console.error('Error fetching clinics:', error)
  }
}

const addClinic = async (clinic) => {
  try {
    // TODO: Add validation for required fields
    if (!clinic.name || !clinic.streetName) {
      alert('Please fill in all required fields.')
      return
    }
    // Add the new clinic to Firestore
    const clinicRef = await addDoc(collection(db, collectionName), clinic)
    clinics.value.push({ id: clinicRef.id, ...clinic })

    alert('Clinic added successfully!')
    clearNewClinic(clinic)
    fetchclinics()
  } catch (error) {
    console.error(`Error adding clinic to ${collectionName}:`, error)
  }
}

// Delete the clinic from the database and remove it from the clinics array
const deleteClinic = async (id) => {
  try {
    const confirmDelete = window.confirm('Are you sure you want to remove this clinic?')
    if (!confirmDelete) {
      return
    }
    // Delete the clinic from Firestore
    await deleteDoc(doc(db, collectionName, id))
    // Remove the clinic from the local array
    clinics.value = clinics.value.filter((clinic) => clinic.id !== id)
    alert('clinic deleted successfully!')
  } catch (error) {
    console.error(`Error deleting clinic from ${collectionName}:`, error)
  }
}

const startEdit = (clinic) => {
  // Copy the clinic data for editing
  editingclinic.value = { ...clinic }
  // Set the editing row ID
  editingRowId.value = clinic.id
}

// Save the edited clinic to the database and update the clinics array
const saveEdit = async (clinic) => {
  try {
    // Update the clinic in Firestore
    const clinicRef = doc(db, collectionName, clinic.id)
    await updateDoc(clinicRef, clinic)
    // Update the clinic in the local array
    const index = clinics.value.findIndex((c) => c.id === clinic.id)
    if (index !== -1) {
      clinics.value[index] = { ...clinic }
    }
    // Clear editing state
    editingclinic.value = null
    editingRowId.value = null

    alert('clinic updated successfully!')
  } catch (error) {
    console.error(`Error updating clinic in ${collectionName}:`, error)
  }
}
// Cancel the editing state
const cancelEdit = () => {
  // Clear the editing clinic
  editingclinic.value = null
  // Clear the editing row ID
  editingRowId.value = null
}
// Clear the new clinic data
const clearNewClinic = (clinic) => {
  // Reset the new clinic data
  Object.keys(clinic).forEach((key) => {
    clinic[key] = '' // Reset all fields
  })
}
</script>
