<template>
  <div class="row justify-content-center">
    <div class="col-md-6">
      <h1 class="text-center mb-4">Register an account</h1>
      <div class="row">
        <form @submit.prevent="submitForm" class="mb-5">
          <div class="row mb-3">
            <div class="col-12 col-md-12">
              <label for="email" class="form-label">Email Address</label>
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
          </div>
          <div class="row mb-3">
            <div class="col-6 col-md-6">
              <label for="password" class="form-label">Password</label>
              <input
                type="password"
                class="form-control"
                id="password"
                @blur="() => validatePassword(true)"
                @input="() => validatePassword(false)"
                v-model="formData.password"
              />
              <div v-if="errors.password" class="text-danger">{{ errors.password }}</div>
            </div>
            <div class="col-6 col-md-6">
              <label for="confirmPassword" class="form-label">Confirm Password</label>
              <input
                type="password"
                class="form-control"
                id="confirmPassword"
                @blur="() => validateConfirmPassword(true)"
                @input="() => validateConfirmPassword(false)"
                v-model="formData.confirmPassword"
              />
              <div v-if="errors.confirmPassword" class="text-danger">
                {{ errors.confirmPassword }}
              </div>
            </div>
          </div>
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
              <div class="gender">
                <label for="gender" class="form-label">gender</label>
                <select
                  class="form-select"
                  id="gender"
                  @blur="() => validategender(true)"
                  @input="() => validategender(false)"
                  v-model="formData.gender"
                >
                  <option value="male">Male</option>
                  <option value="female">Female</option>
                  <option value="other">Other</option>
                </select>
                <div v-if="errors.gender" class="text-danger">{{ errors.gender }}</div>
              </div>
            </div>
            <div class="col-6 col-md-6">
              <label for="dob" class="form-label">Date of Birth</label>
              <VueDatePicker
                v-model="formData.dob"
                :format="'dd/MM/yyyy'"
                :placeholder="'DD/MM/YYYY'"
                @select="onDateSelect"
                @blur="() => validateDob(true)"
                @input="() => validateDob(false)"
              ></VueDatePicker>
              <div v-if="errors.dob" class="text-danger">{{ errors.dob }}</div>
            </div>
          </div>
          <div class="text-center mt-4">
            <button class="btn btn-primary mx-2" @click="register">Register with Firebase</button>
            <button type="button" class="btn btn-secondary mx-2" @click="clearForm">
              Clear form
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import db from '../firebase/init.js'
import { getAuth, createUserWithEmailAndPassword } from 'firebase/auth'
import { doc, setDoc } from 'firebase/firestore'
import { useRouter } from 'vue-router'
import VueDatePicker from '@vuepic/vue-datepicker'
import '@vuepic/vue-datepicker/dist/main.css'

const router = useRouter()
const auth = getAuth()

const formData = ref({
  email: '',
  password: '',
  confirmPassword: '',
  firstname: '',
  surname: '',
  gender: '',
  dob: '',
})

const onDateSelect = (date) => {
  console.log('Selected date:', date)
}

const register = async () => {
  try {
    validateName(true)
    if (
      !errors.value.password &&
      !errors.value.confirmPassword &&
      !errors.value.firstname &&
      !errors.value.surname &&
      !errors.value.email &&
      !errors.value.gender &&
      !errors.value.dob
    ) {
      const userCredential = await createUserWithEmailAndPassword(
        auth,
        formData.value.email,
        formData.value.password,
      )
      const user = userCredential.user

      console.log('Firebase Register successful!')

      const role = formData.value.email === 'admin@gmail.com' ? 'admin' : 'user'

      await setDoc(doc(db, 'users', user.uid), {
        email: formData.value.email,
        firstname: formData.value.firstname,
        surname: formData.value.surname,
        gender: formData.value.gender,
        dob: formData.value.dob,
        role: role,
      })

      alert('Form submitted successfully!')
      router.push({ name: 'Login' })
      clearForm()
    }
  } catch (error) {
    console.error('Error submitting form:', error)
  }
}

const clearForm = () => {
  formData.value = {
    email: '',
    password: '',
    confirmPassword: '',
    firstname: '',
    surname: '',
    gender: '',
    dob: '',
  }
}

const errors = ref({
  email: null,
  password: null,
  confirmPassword: null,
  firstname: null,
  surname: null,
  gender: null,
  dob: null,
})

const validateEmail = (blur) => {
  const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/
  if (!emailPattern.test(formData.value.email)) {
    if (blur) errors.value.email = 'Not a vaild email address'
  } else {
    errors.value.email = null
  }
}
const validatePassword = (blur) => {
  const password = formData.value.password
  const minLength = 8
  const hasUpperCase = /[A-Z]/.test(password)
  const hasLowerCase = /[a-z]/.test(password)
  const hasNumber = /\d/.test(password)
  const hasSpecialChar = /[!@#$%^&*(),.?":{}|<>]/.test(password)

  if (password.length < minLength) {
    if (blur) errors.value.password = `Password must be at least ${minLength} characters long`
  } else if (!hasUpperCase) {
    if (blur) errors.value.password = 'Password must contain at least one uppercase letter'
  } else if (!hasLowerCase) {
    if (blur) errors.value.password = 'Password must contain at least one lowercase letter'
  } else if (!hasNumber) {
    if (blur) errors.value.password = 'Password must contain at least one number'
  } else if (!hasSpecialChar) {
    if (blur) errors.value.password = 'Password must contain at least one special character'
  } else {
    errors.value.password = null
  }
}

const validateConfirmPassword = (blur) => {
  if (formData.value.password !== formData.value.confirmPassword) {
    if (blur) errors.value.confirmPassword = 'Passwords do not match.'
  } else {
    errors.value.confirmPassword = null
  }
}
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
const validategender = (blur) => {
  if (formData.value.gender === '') {
    if (blur) errors.value.gender = 'gender is required'
  } else {
    errors.value.usermessage = null
  }
}
const validateDob = (blur) => {
  if (!formData.value.dob) {
    if (blur) errors.value.dob = 'Date of birth is required'
  } else {
    errors.value.dob = null
  }
}
</script>
