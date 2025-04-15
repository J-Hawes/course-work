<template>
  <h1 id="contact" class="text-center my-5">Contact Us</h1>
  <div class="row">
    <div class="col-md-6">
      <div>
        <strong>Opening Hours</strong>
        <p>Monday to Friday: 9am - 5pm</p>
      </div>
      <div>
        <strong>Phone Number</strong>
        <p>+61 2 1234 5678</p>
      </div>
      <div>
        <strong>Address</strong>
        <p>123 High Street, Sydney, NSW 2000</p>
      </div>
      <div>
        <!-- Generate google map iframe for the above address using an API-free version -->
        <iframe
          width="100%"
          height="330"
          loading="lazy"
          crossorigin="anonymous"
          marginheight="0"
          marginwidth="0"
          src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3151.
          835434509374!2d144.9630579153169!3d-37.81627974202198!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.
          1!3m3!1m2!1s0x6ad642af0f11fd81%3A0xf577d8b8c8b8b8b8!2s
          123%20High%20Street%2C%20Sydney%2C%20NSW%202000!
          5e0!3m2!1sen!2sau!4v1610000000000!5m2!1sen!2sau"
        ></iframe>
      </div>
    </div>
    <div class="row col-md-6">
      <p>
        <strong>
          Whether you want to volunteer, donate, inquire or just want to say hello, we would love to
          hear from you, send us a message.
        </strong>
      </p>
      <form @submit.prevent="submitForm" class="mb-5">
        <div class="row mb-3">
          <div class="col-6 col-md-6">
            <label for="firstname" class="form-label">First Name</label>
            <input
              v-model="formData.firstname"
              type="text"
              class="form-control"
              id="firstname"
              required
              @blur="() => validateName(formData.firstname, 'firstname', errors, true)"
              @input="() => validateName(formData.firstname, 'firstname', errors, false)"
            />
            <div v-if="errors.firstname" class="text-danger">{{ errors.firstname }}</div>
          </div>
          <div class="col-6 col-md-6">
            <label for="surname" class="form-label">Surname</label>
            <input
              v-model="formData.surname"
              type="text"
              class="form-control"
              id="surname"
              required
              @blur="() => validateName(formData.surname, 'surname', errors, true)"
              @input="() => validateName(formData.surname, 'surname', errors, false)"
            />
            <div v-if="errors.surname" class="text-danger">{{ errors.surname }}</div>
          </div>
        </div>
        <div class="row mb-3">
          <div class="col-6 col-md-6">
            <label for="email" class="form-label">Email</label>
            <input
              v-model="formData.email"
              type="email"
              class="form-control"
              id="email"
              required
              @blur="() => validateEmail(formData.email, errors, true)"
              @input="() => validateEmail(formData.email, errors, false)"
            />
            <div v-if="errors.email" class="text-danger">{{ errors.email }}</div>
          </div>
          <div class="col-6 col-md-6">
            <label for="phone" class="form-label">Phone</label>
            <input
              v-model="formData.phone"
              type="tel"
              class="form-control"
              id="phone"
              pattern="[0-9]{10}"
              required
              @blur="() => validatePhone(formData.phone, errors, true)"
              @input="() => validatePhone(formData.phone, errors, false)"
            />
            <div v-if="errors.phone" class="text-danger">{{ errors.phone }}</div>
          </div>
        </div>
        <div class="col-12 col-md-12">
          <div class="subject">
            <label for="subject" class="form-label">Subject</label>
            <select
              v-model="formData.subject"
              class="form-select"
              id="subject"
              required
              @blur="() => validateSubject(formData.subject, errors, true)"
              @input="() => validateSubject(formData.subject, errors, false)"
            >
              <option value="volunteer">Volunteer</option>
              <option value="donate">Donate</option>
              <option value="enquire">Enquire</option>
              <option value="general">General</option>
            </select>
            <div v-if="errors.subject" class="text-danger">{{ errors.subject }}</div>
          </div>
        </div>
        <div class="mb-3">
          <label for="userMessage" class="form-label">Message</label>
          <textarea
            v-model="formData.usermessage"
            class="form-control"
            id="userMessage"
            placeholder="Please enter your message here"
            required
            minlength="10"
            maxlength="500"
            rows="7"
            @blur="() => validateMessage(formData.usermessage, errors, true)"
            @input="() => validateMessage(formData.usermessage, errors, false)"
          ></textarea>
          <div v-if="errors.usermessage" class="text-danger">
            {{ errors.usermessage }}
          </div>
        </div>
        <div class="text-center">
          <button type="submit" class="btn btn-primary me-2">Submit</button>
          <button type="button" class="btn btn-secondary" @click="clearForm">Clear</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import db from '../firebase/init.js'
import { collection, addDoc, serverTimestamp } from 'firebase/firestore'
import {
  validateName,
  validateEmail,
  validatePhone,
  validateSubject,
  validateMessage,
} from '@/helpers/validation.js'

const formData = ref({
  firstname: '',
  surname: '',
  email: '',
  phone: '',
  subject: '',
  usermessage: '',
})

const errors = ref({
  firstname: null,
  surname: null,
  email: null,
  phone: null,
  subject: null,
  usermessage: null,
})

// Function to validate the form, then submit and add data to Firestore
const submitForm = async () => {
  try {
    validateName(formData.value.firstname, formData.value.surname, errors.value, true)
    validateEmail(formData.value.email, errors.value, true)
    validatePhone(formData.value.phone, errors.value, true)
    validateSubject(formData.value.subject, errors.value, true)
    validateMessage(formData.value.usermessage, errors.value, true)
    if (
      !errors.value.firstname &&
      !errors.value.surname &&
      !errors.value.email &&
      !errors.value.phone &&
      !errors.value.subject &&
      !errors.value.usermessage
    ) {
      await addDoc(collection(db, 'contactFormMessages'), {
        firstname: formData.value.firstname,
        surname: formData.value.surname,
        email: formData.value.email,
        phone: formData.value.phone,
        subject: formData.value.subject,
        usermessage: formData.value.usermessage,
        date: serverTimestamp(),
      })
      alert('Message submitted successfully!')
    }
  } catch (error) {
    console.error('Error submitting form:', error)
  } finally {
    clearForm()
  }
}

const clearForm = () => {
  formData.value = {
    firstname: '',
    surname: '',
    email: '',
    phone: '',
    subject: '',
    usermessage: '',
  }
}
</script>
<style scoped>
h1 {
  text-align: center;
}
</style>
