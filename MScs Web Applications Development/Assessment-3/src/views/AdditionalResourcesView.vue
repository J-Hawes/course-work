<template>
  <div class="container mt-5">
    <h1>Additional Resources</h1>
    We believe that access to reliable information and services is essential for fostering
    well-being. Our curated selection of resources covers health education, mental health support,
    community programs, and advocacy materials tailored for refugees and asylum seekers. These
    resources include:
    <ul class="my-2">
      <li>
        <strong>Health & Well-being Guides</strong> – Practical advice on maintaining physical and
        mental health.
      </li>
      <li>
        <strong>Community Support Networks</strong> – Connecting individuals with local groups and
        assistance programs.
      </li>
      <li>
        <strong>Legal & Social Services</strong> – Information on navigating legal rights, housing,
        and employment support.
      </li>
      <li>
        <strong>Multilingual Resources</strong> – Culturally tailored healthcare and support
        information available in multiple languages.
      </li>
    </ul>
    <p>
      We are committed to providing the most relevant and up-to-date information to support the
      needs of refugees and asylum seekers. Our resources are designed to be accessible and
      user-friendly, ensuring that individuals can easily find the information they need. We
      understand that navigating the complexities of health and well-being can be challenging,
      especially for those from diverse backgrounds. Therefore, we have made it our mission to
      create resources that are not only informative but also culturally sensitive and inclusive. We
      encourage you to explore our resources and share them with others who may benefit from them.
      Together, we can work towards a healthier and more informed community.
    </p>
    <hr />
    <div class="faqs">
      <FrequentQuestions />
    </div>
    <hr />
    <div class="download" v-if="isAuthenticated">
      <DownloadeGuides />
      <hr />
    </div>
    <div class="law">
      <LegalRights />
    </div>
    <hr />
  </div>
</template>

<script setup>
import { computed } from 'vue'
import { useAuthStore } from '@/stores/auth'
import DownloadeGuides from './DownloadGuides.vue'
import FrequentQuestions from './FrequentQuestions.vue'
import LegalRights from './LegalRights.vue'

const authStore = useAuthStore()
const isAuthenticated = computed(() => authStore.isAuthenticated)
</script>

<style scoped>
h1 {
  text-align: center;
}
</style>
