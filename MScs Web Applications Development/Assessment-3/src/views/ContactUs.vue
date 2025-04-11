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
        <iframe
          width="100%"
          height="330"
          loading="lazy"
          crossorigin="anonymous"
          marginheight="0"
          marginwidth="0"
          src="https://maps.google.com/maps?width=100%25&amp;height=330&amp;hl=en&amp;q=123%20High%20Street,%20Sydney,%20NSW%202000+()
              &amp;t=&amp;z=17&amp;ie=UTF8&amp;iwloc=B&amp;output=embed"
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
              type="text"
              class="form-control"
              id="firstname"
              @blur="() => validateName(true)"
              @input="() => validateName(false)"
              v-model="formData.firstname"
            />
            <div v-if="errors.firstname" class="text-danger">{{ errors.firstname }}</div>
          </div>
          <div class="col-6 col-md-6">
            <label for="surname" class="form-label">Surname</label>
            <input
              type="text"
              class="form-control"
              id="surname"
              @blur="() => validateName(true)"
              @input="() => validateName(false)"
              v-model="formData.surname"
            />
            <div v-if="errors.surname" class="text-danger">{{ errors.surname }}</div>
          </div>
        </div>
        <div class="row mb-3">
          <div class="col-6 col-md-6">
            <label for="email" class="form-label">Email</label>
            <input
              type="text"
              class="form-control"
              id="email"
              @blur="() => validateEmail(true)"
              @input="() => validateEmail(false)"
              v-model="formData.email"
            />
            <div v-if="errors.email" class="text-danger">{{ errors.email }}</div>
          </div>
          <div class="col-6 col-md-6">
            <label for="phone" class="form-label">Phone</label>
            <input
              type="text"
              class="form-control"
              id="phone"
              @blur="() => validatePhone(true)"
              @input="() => validatePhone(false)"
              v-model="formData.phone"
            />
            <div v-if="errors.phone" class="text-danger">{{ errors.phone }}</div>
          </div>
        </div>
        <div class="col-12 col-md-12">
          <div class="subject">
            <label for="subject" class="form-label">Subject</label>
            <select
              class="form-select"
              id="subject"
              @blur="() => validateSubject(true)"
              @input="() => validateSubject(false)"
              v-model="formData.subject"
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
            class="form-control"
            id="userMessage"
            rows="7"
            @blur="() => validateMessage(true)"
            @input="() => validateMessage(false)"
            v-model="formData.usermessage"
          ></textarea>
          <div v-if="errors.usermessage" class="test-danger">
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
import { collection, addDoc } from 'firebase/firestore'

const formData = ref({
  firstname: '',
  surname: '',
  email: '',
  phone: '',
  subject: '',
  usermessage: '',
})

const submitForm = async () => {
  try {
    validateName(true)
    if (
      !errors.value.firstname &&
      !errors.value.surname &&
      !errors.value.email &&
      !errors.value.phone &&
      !errors.value.subject &&
      !errors.value.usermessage
    ) {
      await addDoc(collection(db, 'contact'), {
        firstname: formData.value.firstname,
        surname: formData.value.surname,
        email: formData.value.email,
        phone: formData.value.phone,
        subject: formData.value.subject,
        usermessage: formData.value.usermessage,
      })
      alert('Form submitted successfully!')
    }
  } catch (error) {
    console.error('Error submitting form:', error)
  } finally {
    formData.value = {
      firstname: '',
      surname: '',
      email: '',
      phone: '',
      subject: '',
      usermessage: '',
    }
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

const errors = ref({
  firstname: null,
  surname: null,
  email: null,
  phone: null,
  subject: null,
  usermessage: null,
})

const validateName = (blur) => {
  if (formData.value.firstname.length < 3) {
    if (blur) errors.value.firstname = 'First name must be at least 3 characters'
  } else if (formData.value.surname.length < 3) {
    if (blur) errors.value.surname = 'Surname must be at least 3 characters'
  } else {
    errors.value.firstname = null
    errors.value.surname = null
  }
}

const validateSubject = (blur) => {
  if (formData.value.subject === '') {
    if (blur) errors.value.subject = 'Subject is required'
  } else {
    errors.value.usermessage = null
  }
}

const validateEmail = (blur) => {
  const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/
  if (!emailPattern.test(formData.value.email)) {
    if (blur) errors.value.email = 'Not a vaild email address'
  } else {
    errors.value.email = null
  }
}

const validatePhone = (blur) => {
  const phonePattern = /^\d{10}$/
  if (!phonePattern.test(formData.value.phone)) {
    if (blur) errors.value.phone = 'Phone number must be 10 digits'
  } else {
    errors.value.phone = null
  }
}

const validateMessage = (blur) => {
  if (formData.value.usermessage.length < 10) {
    if (blur) errors.value.usermessage = 'Message must be at least 10 characters'
    else {
      errors.value.usermessage = null
    }
  }
}
</script>
<style scoped>
h1 {
  text-align: center;
}
</style>
