<template>
  <div class="row justify-content-center">
    <div class="col-md-6">
      <h1 class="text-center">Add a book</h1>
      <form @submit.prevent="addBook">
        <div class="mb-3">
          <label for="isbn">ISBN:</label>
          <input type="text" class="form-control" v-model="isbn" id="isbn" required />
        </div>
        <div class="mb-3">
          <label for="isbn">Name:</label>
          <input type="text" class="form-control" v-model="name" id="name" required />
        </div>
        <div class="text-center mt-4">
          <button class="btn btn-primary" type="submit">Add Book</button>
        </div>
      </form>
    </div>
    <Booklist />
  </div>
</template>

<script setup>
import { ref } from 'vue'
import db from '../Firebase/init.js'
import { collection, addDoc } from 'firebase/firestore'
import Booklist from '../components/Booklist.vue'

const isbn = ref('')
const name = ref('')

const addBook = async () => {
  try {
    const isbnNumber = Number(isbn.value)
    if (isNaN(isbnNumber)) {
      alert('ISBN must be a valid number')
      return
    }
    await addDoc(collection(db, 'books'), {
      isbn: isbnNumber,
      name: name.value,
    })
    isbn.value = ''
    name.value = ''
    alert('Book added successfully!')
  } catch (error) {
    console.error('Error adding book:', error)
  }
}
</script>
