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
                v-model="formData.email"
                type="text"
                class="form-control"
                id="email"
                @blur="() => validateEmail(formData.email, errors, true)"
                @input="() => validateEmail(formData.email, errors, false)"
              />
              <div v-if="errors.email" class="text-danger">{{ errors.email }}</div>
            </div>
          </div>
          <div class="row mb-3">
            <div class="col-6 col-md-6">
              <label for="password" class="form-label">Password</label>
              <input
                v-model="formData.password"
                type="password"
                class="form-control"
                id="password"
                @blur="() => validatePassword(formData.password, errors, true)"
                @input="() => validatePassword(formData.password, errors, false)"
              />
              <div v-if="errors.password" class="text-danger">{{ errors.password }}</div>
            </div>
            <div class="col-6 col-md-6">
              <label for="confirmPassword" class="form-label">Confirm Password</label>
              <input
                type="password"
                class="form-control"
                id="confirmPassword"
                @blur="
                  () =>
                    validateConfirmPassword(
                      formData.password,
                      formData.confirmPassword,
                      errors,
                      true,
                    )
                "
                @input="() => validateConfirmPassword(formData.confirmPassword, errors, false)"
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
                @blur="() => validateName(formData.firstname, 'firstname', errors, true)"
                @input="() => validateName(formData.firstname, 'firstname', errors, false)"
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
                @blur="() => validateName(formData.firstname, 'surname', errors, true)"
                @input="() => validateName(formData.firstname, 'surname', errors, false)"
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
                  v-model="formData.gender"
                  class="form-select"
                  id="gender"
                  @blur="() => validateGender(formData.gender, errors, true)"
                  @input="() => validateGender(formData.gender, errors, false)"
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
                @blur="() => validateDob(formData.dob, errors, true)"
                @input="() => validateDob(formData.dob, errors, false)"
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
import {
  validateEmail,
  validatePassword,
  validateConfirmPassword,
  validateName,
  validateGender,
  validateDob,
} from '@/helpers/validation.js'

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

const errors = ref({
  email: null,
  password: null,
  confirmPassword: null,
  firstname: null,
  surname: null,
  gender: null,
  dob: null,
})

const register = async () => {
  try {
    validateEmail(formData.value.email, errors.value, true)
    validatePassword(formData.value.password, errors.value, true)
    validateConfirmPassword(
      formData.value.password,
      formData.value.confirmPassword,
      errors.value,
      true,
    )
    validateName(formData.value.firstname, formData.value.surname, errors.value, true)
    validateGender(formData.value.gender, errors.value, true)
    validateDob(formData.value.dob, errors.value, true)
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
    if (error.code === 'auth/email-already-in-use') {
      errors.value.email = 'This email is already in use. Please use a different email.'
    } else if (error.code === 'auth/invalid-email') {
      errors.value.email = 'The email address is not valid.'
    } else {
      console.error('Error submitting form:', error)
    }
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
</script>
