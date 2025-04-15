<template>
  <div class="container">
    <h1>User Dashboard</h1>
    <p>Welcome to the user dashboard!</p>
  </div>

  <div>
    <AdminClinicList />
  </div>
</template>

<script setup>
import AdminClinicList from '@/components/AdminClinicList.vue'
</script>

<style scoped></style>
