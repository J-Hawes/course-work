<template>
  <div class="container">
    <h1>Services Directory</h1>
    <p>Welcome to the Services Directory page!</p>
    <p>Here you can find a list of services available to you.</p>

    <div class="content">
      <component :is="currentComponent" />
    </div>
  </div>
</template>

<script setup>
import { ref, watch } from 'vue'
import { useRoute } from 'vue-router'
import ClinicList from '../components/ClinicList.vue'

const currentComponent = ref(null)
const route = useRoute()

// Function to load the component based on the route query parameters
function loadComponent(services) {
  if (services) {
    currentComponent.value = ClinicList
  } else {
    currentComponent.value = null
  }
}

// Watch for changes in the route query parameters
// and load the component accordingly
watch(
  () => route.query.services,
  (newCollection) => {
    loadComponent(newCollection)
  },
  { immediate: true },
)
</script>

<style scoped>
h1 {
  text-align: center;
}
</style>
