<template>
  <div>
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
    <div>
      <h2 id="faqs">Frequently Asked Questions</h2>
      <ul class="my-2">
        <li>
          <strong>Who can receive support from us?</strong>
          We provide services for refugees, asylum seekers, and individuals from underrepresented
          communities who need health and well-being assistance.
        </li>
        <li>
          <strong>Is healthcare free or subsidized?</strong> Depending on eligibility, certain
          medical and mental health services may be free or available at reduced costs. We also help
          individuals navigate government and community healthcare programs.
        </li>
        <li>
          <strong>How can I access mental health support?</strong> We offer referrals to trusted
          professionals, peer support programs, and culturally sensitive mental health services to
          ensure individuals receive the care they need.
        </li>
        <li>
          <strong>Can I volunteer or donate to support your work?</strong> Absolutely! We welcome
          volunteers and donors who want to contribute to improving access to healthcare and
          resources for refugees and asylum seekers. Contact us to learn more about how you can
          help.
        </li>
      </ul>
      Have more questions? Feel free to reach out to us!
    </div>
    <hr />
    <div v-if="isAuthenticated">
      <h2 id="download">Downloadable Guides</h2>
      <p>
        For easy access to vital information, you can download these helpful guides and resources:
      </p>
      <div class="my-2">
        <p>
          <strong>Baby Map</strong> is aimed at people from a refugee background but can be used by
          anyone with small children. While every baby develops differently, the need for bonding
          between parent/caregiver and child is universal. Baby Map can be used in:
        </p>
        <ul>
          <li>The home</li>
          <li>In clinics</li>
          <li>In therapeutic groups</li>
          <li>Parent education courses</li>
          <li>Child and family health consults</li>
          <li>GP clinics</li>
          <li>Playgroup settings</li>
        </ul>
        <a
          href="https://www.startts.org.au/media/Baby-Map-Guide_STARTTS-refugees-trauma_2023-1.pdf"
          class="btn btn-primary"
          target="_blank"
        >
          Download the Baby Map Guide
        </a>
      </div>
      <hr />
    </div>

    <div>
      <h2 id="law">Legal rights and support</h2>
      Navigating legal systems can be challenging, especially for those new to Australia. We provide
      guidance on essential legal rights to ensure refugees and asylum seekers can access fair
      treatment, protections, and essential services.
      <p>Key areas of legal support include:</p>
      <ul class="my-2">
        <li>
          <strong>Visa & Residency Information</strong>
          Understanding immigration pathways and status.
        </li>
        <li>
          <strong>Work & Housing Rights</strong> Rights regarding employment, fair wages, and access
          to housing.
        </li>
        <li>
          <strong>Healthcare Access Protections</strong> Laws ensuring medical care is available to
          those in need.
        </li>
        <li>
          <strong>Legal Aid Referrals</strong> Connecting individuals with legal professionals
          specializing in refugee and asylum cases.
        </li>
      </ul>
      We work alongside advocacy organizations and legal experts to ensure individuals receive the
      information and assistance they need. If you require legal guidance, please reach out to us
      for support or referrals.
    </div>
    <hr />
  </div>
</template>

<script setup></script>
