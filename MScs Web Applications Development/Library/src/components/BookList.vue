<template>
  <div class="row justify-content-center">
    <div class="col-md-6">
      <h1 class="text-center mt-4">Book List</h1>
      <table class="table table-striped">
        <thead>
          <tr>
            <th>ISBN</th>
            <th>Name</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="book in books" :key="book.id">
            <td>{{ book.isbn }}</td>
            <td>{{ book.name }}</td>
            <td>
              <button class="btn btn-warning btn-sm me-2" @click="startEdit(book)">Edit</button>
            </td>
            <td>
              <button class="btn btn-danger btn-sm" @click="deleteBook(book.id)">Delete</button>
            </td>
          </tr>
        </tbody>
      </table>

      <div v-if="editingBook" class="mt-4">
        <h2>Edit Book</h2>
        <form @submit.prevent="editBook">
          <div class="mb-3">
            <label for="isbn">ISBN:</label>
            <input type="text" class="form-control" v-model="editingBook.isbn" id="isbn" required />
          </div>
          <div class="mb-3">
            <label for="name">Name:</label>
            <input type="text" class="form-control" v-model="editingBook.name" id="name" required />
          </div>
          <button class="btn btn-primary" type="submit">Save Changes</button>
          <button class="btn btn-secondary ms-2" @click="cancelEdit">Cancel</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import db from '../Firebase/init.js'
import {
  collection,
  getDocs,
  updateDoc,
  deleteDoc,
  doc,
  query,
  where,
  orderBy,
  limit,
} from 'firebase/firestore'

const books = ref([])
const editingBook = ref(null)

const fetchBooks = async () => {
  try {
    const q = query(collection(db, 'books'), where('isbn', '>', 1000), orderBy('isbn'), limit(10))
    const querySnapshot = await getDocs(q)
    const booksArray = []
    querySnapshot.forEach((doc) => {
      booksArray.push({ id: doc.id, ...doc.data() })
    })
    books.value = booksArray
  } catch (error) {
    console.error('Error fetching books:', error)
  }
}

const startEdit = (book) => {
  editingBook.value = { ...book }
}

const editBook = async () => {
  try {
    const isbnNumber = Number(editingBook.value.isbn)
    if (isNaN(isbnNumber)) {
      alert('ISBN must be a valid number')
      return
    }

    await updateDoc(doc(db, 'books', editingBook.value.id), {
      isbn: isbnNumber,
      name: editingBook.value.name,
    })

    const index = books.value.findIndex((book) => book.id === editingBook.value.id)
    if (index !== -1) {
      books.value[index] = { ...editingBook.value }
    }

    editingBook.value = null
    alert('Book updated successfully!')
  } catch (error) {
    console.error('Error updating book:', error)
  }
}

const cancelEdit = () => {
  editingBook.value = null
}

const deleteBook = async (id) => {
  try {
    await deleteDoc(doc(db, 'books', id))
    books.value = books.value.filter((book) => book.id !== id)
    alert('Book deleted successfully!')
  } catch (error) {
    console.error('Error deleting book:', error)
  }
}

onMounted(() => {
  fetchBooks()
})
</script>
