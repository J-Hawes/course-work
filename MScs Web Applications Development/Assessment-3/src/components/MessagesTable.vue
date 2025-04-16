<template>
  <div class="row justify-content-center">
    <div class="col-md-12">
      <h1 class="text-center mt-4">Contact form {{ title }}</h1>
      <table class="table table-striped d-md-table">
        <thead>
          <tr>
            <th style="width: 10%">Date</th>
            <th style="width: 20%">Sender</th>
            <th style="width: 60%">Subject</th>
            <th style="width: 5%">Read</th>
            <th style="width: 5%">Delete</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="message in messages" :key="message.id">
            <td>{{ formatDate(message.date) }}</td>
            <td>{{ message.email }}</td>
            <td>{{ message.subject }}</td>
            <td>
              <button
                class="btn btn-warning btn-sm me-2"
                @click="viewMessage(message)"
                data-bs-toggle="modal"
                data-bs-target="#messageModal"
              >
                View
              </button>
            </td>
            <td>
              <button class="btn btn-danger btn-sm" @click="deleteMessage(message.id)">
                Delete
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>

  <!-- Modal -->
  <div
    class="modal fade"
    id="messageModal"
    tabindex="-1"
    aria-labelledby="messageModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="messageModalLabel">Message Details</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <div class="modal-body">
          <p><strong>Date:</strong> {{ formatDate(selectedMessage.date) }}</p>
          <p>
            <strong>Sender:</strong> {{ selectedMessage.firstname }}, {{ selectedMessage.surname }}
          </p>
          <p><strong>Email Address:</strong> {{ selectedMessage.email }}</p>
          <p><strong>Contact Number:</strong> {{ selectedMessage.phone }}</p>
          <p><strong>Subject:</strong> {{ selectedMessage.subject }}</p>
          <p><strong>Message:</strong> {{ selectedMessage.usermessage }}</p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary" @click="printMessage">Print</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getDocs, collection, deleteDoc, doc } from 'firebase/firestore'
import db from '@/firebase/init'

// Reactive variable to store messages
const messages = ref([])

// Reactive variable to store the selected message for the modal
const selectedMessage = ref({})

const { collectionName, title } = defineProps({
  collectionName: String,
  title: String,
})

// Fetch messages from Firestore
const loadMessages = async () => {
  try {
    const messagesSnapshot = await getDocs(collection(db, collectionName))
    messages.value = messagesSnapshot.docs.map((doc) => ({
      id: doc.id,
      ...doc.data(),
    }))
  } catch (error) {
    console.error('Error fetching messages:', error)
  }
}

// Format timestamp to dd/mm/yyyy
const formatDate = (timestamp) => {
  if (!timestamp) return 'N/A'
  const date = timestamp.toDate ? timestamp.toDate() : new Date(timestamp)
  const day = String(date.getDate()).padStart(2, '0')
  const month = String(date.getMonth() + 1).padStart(2, '0') // Months are 0-based
  const year = date.getFullYear()
  return `${day}/${month}/${year}`
}

// View message details (placeholder function)
const viewMessage = (message) => {
  selectedMessage.value = message
}

// Print message details
const printMessage = () => {
  if (!selectedMessage.value) return

  const printContent = `
    <div>
      <h3>Message Details</h3>
      <p><strong>Date:</strong> ${formatDate(selectedMessage.value.date)}</p>
      <p><strong>Sender:</strong> ${selectedMessage.value.firstname}, ${selectedMessage.value.surname}</p>
      <p><strong>Email Address:</strong> ${selectedMessage.value.email}</p>
      <p><strong>Contact Number:</strong> ${selectedMessage.value.phone}</p>
      <p><strong>Subject:</strong> ${selectedMessage.value.subject}</p>
      <p><strong>Message:</strong> ${selectedMessage.value.usermessage}</p>
    </div>
  `

  const printWindow = window.open('', '_blank')
  printWindow.document.write(printContent)
  printWindow.document.close()
  printWindow.print()
}

// Delete a message from Firestore
const deleteMessage = async (id) => {
  const confirmDelete = window.confirm('Are you sure you want to delete this message?')
  if (!confirmDelete) return

  try {
    await deleteDoc(doc(db, 'contactFormMessages', id))
    messages.value = messages.value.filter((message) => message.id !== id)
    alert('Message deleted successfully!')
  } catch (error) {
    console.error('Error deleting message:', error)
  }
}

// Load messages on component mount
onMounted(() => {
  loadMessages()
})
</script>

<style scoped>
.table input {
  width: 100%;
  padding: 0.5rem;
  font-size: 0.9rem;
}

/* Shrink table for smaller screens */
@media (max-width: 576px) {
  .table {
    font-size: 0.7rem;
  }

  .table th,
  .table td {
    padding: 0.3rem;
  }

  .btn {
    font-size: 0.6rem;
    padding: 0.2rem 0.4rem;
  }
}

/* Shrink modal for smaller screens */
@media (max-width: 576px) {
  .modal-dialog {
    max-width: 90%;
    margin: 0.5rem auto;
  }

  .modal-content {
    font-size: 0.8rem;
  }

  .modal-header h5 {
    font-size: 1rem;
  }

  .modal-body p {
    font-size: 0.8rem;
  }

  .modal-footer .btn {
    font-size: 0.7rem;
    padding: 0.2rem 0.4rem;
  }
}
</style>
