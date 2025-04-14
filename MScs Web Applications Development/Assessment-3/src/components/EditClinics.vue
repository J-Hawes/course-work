<template>
  <div class="row justify-content-center">
    <div class="col-md-12">
      <h1 class="text-center mt-4">clinic List</h1>

      <!-- Button to Open Modal -->
      <div class="d-flex justify-content-end mb-3">
        <button
          class="btn btn-primary mb-3"
          data-bs-toggle="modal"
          data-bs-target="#addClinicModal"
        >
          Add New Clinic
        </button>
      </div>

      <!-- Desktop View (Table) -->
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
                <button class="btn btn-warning btn-sm me-2" @click="startEdit(clinic)">
                  <i class="material-icons">edit</i>
                </button>
              </td>
              <td>
                <button class="btn btn-danger btn-sm" @click="deleteclinic(clinic.id)">
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
                  v-model="editingclinic.name"
                />
              </td>
              <td>
                <div class="row">
                  <div class="col-4">
                    <input
                      type="text"
                      id="streetNumber{{ clinic.id }}"
                      class="form-control"
                      v-model.number="editingclinic.streetNumber"
                    />
                  </div>
                  <div class="col-8">
                    <input
                      type="text"
                      id="streetName{{ clinic.id }}"
                      class="form-control"
                      v-model="editingclinic.streetName"
                    />
                  </div>
                </div>
              </td>
              <td>
                <input
                  type="text"
                  id="suburb{{ clinic.id }}"
                  class="form-control"
                  v-model="editingclinic.suburb"
                />
              </td>
              <td>
                <input
                  type="text"
                  id="state{{ clinic.id }}"
                  class="form-control"
                  v-model="editingclinic.state"
                />
              </td>
              <td>
                <input
                  type="text"
                  id="postcode{{ clinic.id }}"
                  class="form-control"
                  v-model.number="editingclinic.postcode"
                />
              </td>
              <td>
                <input
                  type="tel"
                  pattern="[0-9]{10}"
                  id="phone{{ clinic.id }}"
                  class="form-control"
                  v-model.number="editingclinic.phone"
                />
              </td>
              <td>
                <input
                  type="text"
                  id="hours{{ clinic.id }}"
                  class="form-control"
                  v-model="editingclinic.hours"
                />
              </td>
              <td>
                <button class="btn btn-success btn-sm me-2" @click="saveEdit">Save</button>
              </td>
              <td>
                <button class="btn btn-secondary btn-sm" @click="cancelEdit">Cancel</button>
              </td>
            </template>
          </tr>
        </tbody>
      </table>
      <!-- Mobile View displays as cards-->
      <div class="d-md-none">
        <div class="card mb-3" v-for="clinic in clinics" :key="clinic.id">
          <div class="card-body">
            <div class="card-body">
              <template v-if="editingRowId !== clinic.id">
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
                  <button class="btn btn-danger btn-sm" @click="deleteClinic(clinic.id)">
                    <i class="material-icons">delete</i>
                  </button>
                </div>
              </template>
              <!-- Editable Card View -->
              <template v-else>
                <div class="floating-label">
                  <input
                    type="text"
                    id="name-mobile-{{ clinic.id }}"
                    class="form-control mb-2"
                    v-model="editingclinic.name"
                    placeholder=" "
                  />
                  <label for="name-mobile-{{ clinic.id }}">Clinic Name</label>
                </div>

                <div class="floating-label">
                  <input
                    type="text"
                    id="streetNumber-mobile-{{ clinic.id }}"
                    class="form-control mb-2"
                    v-model.number="editingclinic.streetNumber"
                    placeholder=" "
                  />
                  <label for="streetNumber-mobile-{{ clinic.id }}">Street Number</label>
                </div>

                <div class="floating-label">
                  <input
                    type="text"
                    id="streetName-mobile-{{ clinic.id }}"
                    class="form-control mb-2"
                    v-model="editingclinic.streetName"
                    placeholder=" "
                  />
                  <label for="streetName-mobile-{{ clinic.id }}">Street Name</label>
                </div>

                <div class="floating-label">
                  <input
                    type="text"
                    id="suburb-mobile-{{ clinic.id }}"
                    class="form-control mb-2"
                    v-model="editingclinic.suburb"
                    placeholder=" "
                  />
                  <label for="suburb-mobile-{{ clinic.id }}">Suburb</label>
                </div>

                <div class="floating-label">
                  <input
                    type="text"
                    id="state-mobile-{{ clinic.id }}"
                    class="form-control mb-2"
                    v-model="editingclinic.state"
                    placeholder=" "
                  />
                  <label for="state-mobile-{{ clinic.id }}">State</label>
                </div>

                <div class="floating-label">
                  <input
                    type="text"
                    id="postcode-mobile-{{ clinic.id }}"
                    class="form-control mb-2"
                    v-model.number="editingclinic.postcode"
                    placeholder=" "
                  />
                  <label for="postcode-mobile-{{ clinic.id }}">Postcode</label>
                </div>

                <div class="floating-label">
                  <input
                    type="tel"
                    pattern="[0-9]{10}"
                    id="phone-mobile-{{ clinic.id }}"
                    class="form-control mb-2"
                    v-model.number="editingclinic.phone"
                    placeholder=" "
                  />
                  <label for="phone-mobile-{{ clinic.id }}">Phone</label>
                </div>

                <div class="floating-label">
                  <input
                    type="text"
                    id="hours-mobile-{{ clinic.id }}"
                    class="form-control mb-2"
                    v-model="editingclinic.hours"
                    placeholder=" "
                  />
                  <label for="hours-mobile-{{ clinic.id }}">Opening Hours</label>
                </div>

                <div class="d-flex justify-content-end">
                  <button class="btn btn-success btn-sm me-2" @click="saveEdit">Save</button>
                  <button class="btn btn-secondary btn-sm" @click="cancelEdit">Cancel</button>
                </div>
              </template>
            </div>
          </div>
        </div>
      </div>
      <!-- Modal for Adding a New Clinic -->
      <div
        class="modal fade"
        id="addClinicModal"
        tabindex="-1"
        aria-labelledby="addClinicModalLabel"
        aria-hidden="true"
      >
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="addClinicModalLabel">Add New Clinic</h5>
              <button
                type="button"
                class="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>
            <div class="modal-body">
              <!-- Input Fields -->
              <div class="floating-label">
                <input
                  type="text"
                  id="name-modal"
                  class="form-control mb-2"
                  v-model="newClinic.name"
                  placeholder=" "
                />
                <label for="name-modal">Clinic Name</label>
              </div>
              <div class="floating-label">
                <input
                  type="text"
                  id="streetNumber-modal"
                  class="form-control mb-2"
                  v-model.number="newClinic.streetNumber"
                  placeholder=" "
                />
                <label for="streetNumber-modal">Street Number</label>
              </div>
              <div class="floating-label">
                <input
                  type="text"
                  id="streetName-modal"
                  class="form-control mb-2"
                  v-model="newClinic.streetName"
                  placeholder=" "
                />
                <label for="streetName-modal">Street Name</label>
              </div>
              <div class="floating-label">
                <input
                  type="text"
                  id="suburb-modal"
                  class="form-control mb-2"
                  v-model="newClinic.suburb"
                  placeholder=" "
                />
                <label for="suburb-modal">Suburb</label>
              </div>
              <div class="floating-label">
                <input
                  type="text"
                  id="state-modal"
                  class="form-control mb-2"
                  v-model="newClinic.state"
                  placeholder=" "
                />
                <label for="state-modal">State</label>
              </div>
              <div class="floating-label">
                <input
                  type="text"
                  id="postcode-modal"
                  class="form-control mb-2"
                  v-model.number="newClinic.postcode"
                  placeholder=" "
                />
                <label for="postcode-modal">Postcode</label>
              </div>
              <div class="floating-label">
                <input
                  type="tel"
                  pattern="[0-9]{10}"
                  id="phone-modal"
                  class="form-control mb-2"
                  v-model.number="newClinic.phone"
                  placeholder=" "
                />
                <label for="phone-modal">Phone</label>
              </div>
              <div class="floating-label">
                <input
                  type="text"
                  id="hours-modal"
                  class="form-control mb-2"
                  v-model="newClinic.hours"
                  placeholder=" "
                />
                <label for="hours-modal">Opening Hours</label>
              </div>
            </div>
            <div class="modal-footer">
              <button
                type="button"
                class="btn btn-secondary"
                data-bs-dismiss="modal"
                @click="clearNewClinic"
              >
                Clear
              </button>
              <button
                type="button"
                class="btn btn-primary"
                @click="addClinic"
                data-bs-dismiss="modal"
              >
                Add Clinic
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import db from '../firebase/init.js'
import {
  collection,
  getDocs,
  updateDoc,
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

const startEdit = (clinic) => {
  editingclinic.value = { ...clinic }
  editingRowId.value = clinic.id
}

// ToDo fix this for linked query refs
// Save the edited clinic to the database and update the clinics array
const saveEdit = async () => {
  try {
    const clinicRef = doc(db, 'healthClinics', editingclinic.value.id)
    await updateDoc(clinicRef, editingclinic.value)

    const index = clinics.value.findIndex((clinic) => clinic.id === editingclinic.value.id)
    if (index !== -1) {
      clinics.value[index] = { ...editingclinic.value }
    }

    editingclinic.value = null
    editingRowId.value = null
    alert('clinic updated successfully!')
  } catch (error) {
    console.error('Error updating clinic:', error)
  }
}

const cancelEdit = () => {
  editingclinic.value = null
  editingRowId.value = null
}

// TODO fix  this for linked query refs
// Delete the clinic from the database and remove it from the clinics array
const deleteClinic = async (id) => {
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

onMounted(() => {
  fetchclinics()
})
</script>

<style scoped>
.table input {
  width: 100%;
  padding: 0.5rem;
  font-size: 0.9rem;
}

.card {
  border: 1px solid #ddd;
  border-radius: 8px;
  padding: 1rem;
  margin-bottom: 1rem;
}

.card-title {
  font-size: 1.25rem;
  font-weight: bold;
}

.card-text {
  margin-bottom: 0.5rem;
}

.card .d-flex {
  justify-content: flex-end;
}

.card input {
  margin-bottom: 0.5rem;
}

.floating-label {
  position: relative;
  margin-bottom: 1rem;
}

.floating-label input {
  width: 100%;
  padding: 0.75rem 0.75rem 0.25rem;
  font-size: 1rem;
  border: 1px solid #ced4da;
  border-radius: 4px;
  box-sizing: border-box;
}

.floating-label label {
  position: absolute;
  top: 50%;
  left: 0.75rem;
  transform: translateY(-50%);
  font-size: 1rem;
  color: #6c757d;
  pointer-events: none;
  transition: all 0.2s ease-in-out;
}

.floating-label input:focus + label,
.floating-label input:not(:placeholder-shown) + label {
  top: 0.25rem;
  font-size: 0.75rem;
  color: #495057;
}

.floating-label input:focus {
  outline: none;
  border-color: #80bdff;
  box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
}
</style>
