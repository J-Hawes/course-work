<template>
  <nav class="navbar navbar-expand-md navbar-light bg-light">
    <div class="container-fluid">
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarNav"
        aria-controls="navbarNav"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav nav-pills justify-content-center w-100 flex-wrap">
          <li class="nav-item mx-3">
            <router-link to="/" class="nav-link" active-class="active" aria-current="page"
              >Home</router-link
            >
          </li>
          <li class="nav-item dropdown mx-3">
            <router-link
              class="nav-link dropdown-toggle"
              to="/health"
              id="healthDropdown"
              data-bs-toggle="dropdown"
              aria-expanded="false"
              active-class="active"
            >
              Health Resources
            </router-link>
            <ul class="dropdown-menu" aria-labelledby="healthDropdown">
              <li>
                <router-link to="/health" class="dropdown-item">Health Resources</router-link>
              </li>
              <li>
                <router-link to="/health#general" class="dropdown-item"
                  >General Health Information</router-link
                >
              </li>
              <li>
                <router-link to="/health#mental" class="dropdown-item"
                  >Mental Health Resources</router-link
                >
              </li>
              <li>
                <router-link to="/health#women" class="dropdown-item">Women's Health</router-link>
              </li>
              <li>
                <router-link to="/health#children" class="dropdown-item"
                  >Children's Health</router-link
                >
              </li>
            </ul>
          </li>

          <li class="nav-item dropdown mx-3">
            <router-link
              class="nav-link dropdown-toggle"
              to="/services"
              id="servicesDropdown"
              data-bs-toggle="dropdown"
              aria-expanded="false"
              active-class="active"
            >
              Services Directory
            </router-link>
            <!-- Query provided to be used elsewhere in the application for selecting with path to load-->
            <ul class="dropdown-menu" aria-labelledby="servicesDropdown">
              <li>
                <router-link
                  :to="{ path: '/services', query: { services: 'healthClinics' } }"
                  class="dropdown-item"
                >
                  Health Clinics
                </router-link>
              </li>
              <li>
                <router-link
                  :to="{ path: '/services', query: { services: 'mentalHealthClinics' } }"
                  class="dropdown-item"
                >
                  Mental Health Services
                </router-link>
              </li>
            </ul>
          </li>

          <li class="nav-item dropdown mx-3" v-if="isAuthenticated">
            <router-link
              class="nav-link dropdown-toggle"
              to="/education"
              id="educationDropdown"
              data-bs-toggle="dropdown"
              aria-expanded="false"
              active-class="active"
            >
              Educational Resources
            </router-link>
            <ul class="dropdown-menu" aria-labelledby="educationDropdown">
              <li>
                <router-link to="/education" class="dropdown-item"
                  >Educational Resources</router-link
                >
              </li>
              <li>
                <router-link to="/education#workshop" class="dropdown-item"
                  >Workshops and Webinars</router-link
                >
              </li>
              <li>
                <router-link to="/education#jobs" class="dropdown-item"
                  >Job Opportunities</router-link
                >
              </li>
              <li>
                <router-link to="/education#volunteer" class="dropdown-item"
                  >Volunteer Opportunities</router-link
                >
              </li>
              <li>
                <router-link to="/education#school" class="dropdown-item"
                  >Schooling Information</router-link
                >
              </li>
            </ul>
          </li>

          <li class="nav-item dropdown mx-3" v-if="isAuthenticated">
            <router-link
              class="nav-link dropdown-toggle"
              to="/community"
              id="communityDropdown"
              data-bs-toggle="dropdown"
              aria-expanded="false"
              active-class="active"
            >
              Community Forum
            </router-link>
            <ul class="dropdown-menu" aria-labelledby="communityDropdown">
              <li>
                <router-link to="/community#general" class="dropdown-item"
                  >General Discussions</router-link
                >
              </li>
              <li>
                <router-link to="/community#health" class="dropdown-item"
                  >Health Questions</router-link
                >
              </li>
              <li>
                <router-link to="/community#groups" class="dropdown-item"
                  >Support Groups</router-link
                >
              </li>
            </ul>
          </li>

          <li class="nav-item dropdown mx-3">
            <router-link
              class="nav-link dropdown-toggle"
              to="/additional"
              id="additionalDropdown"
              data-bs-toggle="dropdown"
              aria-expanded="false"
              active-class="active"
            >
              Additional Resources
            </router-link>
            <ul class="dropdown-menu" aria-labelledby="additionalDropdown">
              <li>
                <router-link to="/additional" class="dropdown-item"
                  >Additional Resources</router-link
                >
              </li>
              <li>
                <router-link to="/additional#faqs" class="dropdown-item"
                  >Frequently Asked Questions</router-link
                >
              </li>
              <li v-if="isAuthenticated">
                <router-link to="/additional#download" class="dropdown-item"
                  >Downloadable Guides</router-link
                >
              </li>
              <li>
                <router-link to="/additional#law" class="dropdown-item">Legal Rights</router-link>
              </li>
            </ul>
          </li>

          <li class="nav-item dropdown mx-3">
            <router-link
              to="/about"
              class="nav-link dropdown-toggle"
              id="aboutDropdown"
              data-bs-toggle="dropdown"
              aria-expanded="false"
              active-class="active"
            >
              About Us
            </router-link>
            <ul class="dropdown-menu" aria-labelledby="aboutDropdown">
              <li>
                <router-link to="/about" class="dropdown-item">About Us</router-link>
              </li>
              <li>
                <router-link to="/about#mission" class="dropdown-item">Our Mission</router-link>
              </li>
              <li>
                <router-link to="/about#team" class="dropdown-item">Our Team</router-link>
              </li>
              <li>
                <router-link to="/about#links" class="dropdown-item"
                  >Partner Organisations</router-link
                >
              </li>
              <li>
                <router-link to="/about#contact" class="dropdown-item">Contact Us</router-link>
              </li>
            </ul>
          </li>
        </ul>
      </div>
      <!-- Right side of the navbar options depending on authentication status -->
      <ul class="navbar-nav nav-pills ms-auto">
        <li class="nav-item" v-if="!isAuthenticated">
          <router-link to="/login" class="nav-link" active-class="active">Login</router-link>
        </li>
        <li class="nav-item dropdown ms-auto" v-else>
          <router-link
            to="/user"
            class="nav-link dropdown-toggle"
            id="userDropdown"
            data-bs-toggle="dropdown"
            aria-expanded="false"
            :class="{ active: isActive }"
          >
            <i class="bi bi-person-fill" style="font-size: 2rem"></i>
          </router-link>
          <!-- Dropdown menu for user options depending on role-->
          <ul class="dropdown-menu" aria-labelledby="userDropdown">
            <li v-if="admin">
              <router-link to="/admin" class="dropdown-item">Admin Dashboard</router-link>
            </li>
            <li v-else>
              <router-link to="/user" class="dropdown-item">User Dashboard</router-link>
            </li>

            <li>
              <a href="#" class="dropdown-item" @click.prevent="logout">Logout</a>
            </li>
          </ul>
        </li>
      </ul>
    </div>
  </nav>
</template>

<script setup>
import { useAuthStore } from '@/stores/auth'
import { useRouter, useRoute } from 'vue-router'
import { computed } from 'vue'

const router = useRouter()
const authStore = useAuthStore()

// Reactive admin check
const admin = computed(() => authStore.userRole === 'admin')

const isAuthenticated = computed(() => authStore.isAuthenticated)

// Reactive route object
const route = useRoute()
// Check if the current route is /admin or /user
// To be able to apply styling to the active link on the person icon
const isActive = computed(() => {
  return route.path === '/admin' || route.path === '/user'
})

// Function to log out the user, removing authentication status from session storage, and redirecting to the home page
const logout = () => {
  authStore.logout()
  router.push({ name: 'Home' })
}
</script>

<style scoped>
/* Disable pointer events for dropdown toggles on larger screens */
@media (min-width: 768px) {
  .navbar-nav .dropdown-toggle {
    pointer-events: none;
  }
}
/* Hover behavior for dropdowns on larger screens */
@media (min-width: 768px) {
  .navbar-nav .dropdown:hover .dropdown-menu {
    display: block;
  }
}

.navbar-nav .dropdown-toggle::after {
  display: none;
}
.navbar-toggler-icon {
  width: 1rem;
  height: 1rem;
  background-size: contain;
}

.navbar-nav .dropdown:hover .nav-link {
  color: hsla(160, 100%, 37%, 1);
}
.nav-pills .nav-link.active {
  background-color: hsla(160, 100%, 37%, 0.3);
}
li.dropdown:last-child .dropdown-menu {
  right: 0;
  left: auto;
}
</style>
