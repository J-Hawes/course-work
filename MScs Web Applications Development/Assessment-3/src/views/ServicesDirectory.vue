<template>
  <div class="container">
    <div class="row">
      <div class="col-md-2 mb-2">
        <h4>
          <p class="my-lg-5 my-sm-1"><a href="#"> Health Clinics</a></p>
          <p class="my-lg-5 my-sm-1"><a href="#"> Mental Health Clinics</a></p>
          <p class="my-lg-5 my-sm-1"><a v-if="isAuthenticated" href="#"> Bookings</a></p>
        </h4>
      </div>
      <div class="col-md-10 mb-2">
        <h1>Services Directory</h1>
        <p>Welcome to the Services Directory page!</p>
        <p>Here you can find a list of services available to you.</p>

        <div class="d-flex flex-wrap justify-content-start">
          <div class="card mb-3 w-100">
            <div class="row g-0">
              <div class="col-md-3">
                <img
                  src="../assets/map-template.jpg"
                  class="img-fluid rounded-start"
                  width="100%"
                  alt="..."
                />
              </div>
              <div class="col-md-6">
                <div class="card-body">
                  <h4 class="card-title">Dynamicaly fetch Clinic Name</h4>
                  <ul class="card-text">
                    <li>Adrees: add address from database here</li>
                    <li>Phone: add phone from database here</li>
                    <li>Email: add email from database here</li>
                    <li>Hours: add hours from database here</li>
                  </ul>
                </div>
              </div>
              <div class="col-md-3">
                <div class="text-center">
                  <img
                    src="../assets/map-template.jpg"
                    class="img-fluid rounded-start"
                    width="80%"
                    alt="..."
                  />
                  <button class="btn btn-primary btn-sm w-100" v-if="isAuthenticated">
                    Make a booking
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { isAuthenticated, updateAuthStatus } from '../stores/auth'

updateAuthStatus()
console.log('isAuthenticated', isAuthenticated.value)
</script>
