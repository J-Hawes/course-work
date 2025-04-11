<template>
  <div class="container">
    <h1>Health Resources</h1>
    <div>
      <p>
        Access to healthcare is a fundamental human right, but many refugees and asylum seekers face
        barriers when seeking medical assistance. Our health resources aim to bridge these gaps by
        providing guidance on accessing medical services, understanding healthcare systems, and
        finding culturally sensitive care options
      </p>
      <p><strong>Finding Healthcare Services</strong></p>
      Refugees and asylum seekers may not always be aware of the healthcare services available to
      them. Here’s how to find the right care:
      <ul class="my-2">
        <li>
          <strong>Public Healthcare System</strong> – Understanding Medicare eligibility and
          navigating government-funded medical services.
        </li>
        <li>
          <strong>Community Health Clinics</strong> – Local health centers providing low-cost or
          free healthcare services tailored for underserved populations.
        </li>
        <li>
          <strong>Specialized Medical Programs</strong> – Refugee health initiatives offering
          culturally appropriate care, including language support.
        </li>
        <li>
          <strong>Emergency & Crisis Care</strong> – Information on when to visit a hospital, how to
          access urgent medical assistance, and emergency contacts.
        </li>
      </ul>
      <p><strong>Free & Low-Cost Healthcare Providers</strong></p>
      Many organizations and clinics offer free or subsidized medical services for refugees and
      asylum seekers. Some key resources include:
      <ul class="my-2">
        <li>
          <strong>Nonprofit medical organizations</strong> – Groups focused on delivering healthcare
          to refugees, often working with hospitals and clinics.
        </li>
        <li>
          <strong>Bulk-Billing GPs</strong> – Doctors who offer services with no out-of-pocket fees
          for eligible patients.
        </li>
        <li>
          <strong>Mental health support groups</strong> – Free counseling, therapy sessions, and
          peer support networks tailored to refugees.
        </li>
        <li>
          <strong>Mobile health services</strong> – Outreach programs that provide medical
          assistance in community centers or shelters.
        </li>
      </ul>
      <p>
        We are committed to making healthcare more accessible to vulnerable communities. If you need
        help finding medical assistance, reach out to us for support. We can connect you with
        healthcare providers, community organizations, and resources that cater to your specific
        needs. Our goal is to ensure that you receive the care you deserve, regardless of your
        background or circumstances.
      </p>
    </div>
    <hr />
    <div class="general">
      <GeneralHealth />
    </div>
    <hr />
    <div class="mental">
      <MentalHealth />
    </div>
    <hr />
    <div class="women">
      <WomensHealth />
    </div>
    <hr />
    <div class="children">
      <ChildrensHealth />
    </div>
  </div>
</template>

<script setup>
import ChildrensHealth from './ChildrensHealth.vue'
import GeneralHealth from './GeneralHealth.vue'
import MentalHealth from './MentalHealth.vue'
import WomensHealth from './WomensHealth.vue'
</script>
