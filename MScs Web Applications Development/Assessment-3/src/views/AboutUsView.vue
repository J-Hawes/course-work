<template>
  <div class="container">
    <div class="about">
      <h1>About Us</h1>
      <p>
        We believe that healthcare should be accessible to all—regardless of background, financial
        situation, or immigration status. Refugees and asylum seekers often face immense challenges
        when trying to access medical care. Language barriers, cultural differences, and a lack of
        familiarity with Australia’s healthcare system can prevent individuals from receiving the
        treatment they need. Our mission is to bridge these gaps, providing compassionate,
        culturally sensitive healthcare support and education tailored to the unique needs of
        displaced communities.
      </p>
      <p>
        Founded on principles of inclusivity and dignity, we work to empower individuals through
        health education, direct medical assistance, mental health support, and advocacy. Beyond
        immediate healthcare needs, we help refugees and asylum seekers navigate the complexities of
        the system, ensuring they have the knowledge and confidence to take control of their own
        well-being.
      </p>
      <p>
        Our programs are designed to be holistic, acknowledging the impact of displacement, trauma,
        and uncertainty on both physical and mental health. From primary healthcare services to
        mental health counseling, legal support, and health literacy workshops, we are committed to
        providing the necessary tools for individuals to build healthier, more stable lives in
        Australia.
      </p>
    </div>
    <div class="mission">
      <OurMission />
    </div>
    <div class="team">
      <OurTeam />
    </div>
    <div class="links">
      <PartnerOrganisations />
    </div>
    <div class="contact my-5">
      <ContactUs />
    </div>
  </div>
</template>

<script setup>
import PartnerOrganisations from './PartnerOrganisations.vue'
import OurTeam from './OurTeam.vue'
import OurMission from './OurMission.vue'
import ContactUs from './ContactUs.vue'
</script>

<style scoped>
h1 {
  text-align: center;
}
</style>
