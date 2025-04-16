<template>
  <div class="container mt-5">
    <h1>Educational Resources</h1>
    <div>
      <p>
        Educational Resources Access to education is a crucial step toward empowerment and
        integration. We provide a wide range of educational resources designed to help refugees and
        asylum seekers gain knowledge, develop skills, and navigate life in Australia confidently.
        Health & Well-being Education Understanding health and wellness is essential for thriving in
        a new country.
      </p>
      <ul>
        <li>Understanding the Australian education system</li>
        <li>Enrolling in schools and educational programs</li>
        <li>Accessing support services for students with special needs</li>
        <li>Finding language and literacy programs</li>
        <li>Connecting with community organizations and resources</li>
        <li>Understanding the rights and responsibilities of students and parents</li>
      </ul>
    </div>
    <hr />
    <div class="workshop">
      <WorkShops />
    </div>
    <hr />
    <div class="jobs">
      <JobOps />
    </div>
    <hr />
    <div class="volunteer">
      <VolunteerOps />
    </div>
    <hr />
    <div class="school">
      <SchoolInfo />
    </div>
  </div>
</template>

<script setup>
import JobOps from './JobOps.vue'
import SchoolInfo from './SchoolInfo.vue'
import VolunteerOps from './VolunteerOps.vue'
import WorkShops from './WorkShops.vue'
</script>
