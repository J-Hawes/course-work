<template>
  <div class="about">
    <h1>About Us</h1>
    <p>
      We believe that healthcare should be accessible to all—regardless of background, financial
      situation, or immigration status. Refugees and asylum seekers often face immense challenges
      when trying to access medical care. Language barriers, cultural differences, and a lack of
      familiarity with Australia’s healthcare system can prevent individuals from receiving the
      treatment they need. Our mission is to bridge these gaps, providing compassionate, culturally
      sensitive healthcare support and education tailored to the unique needs of displaced
      communities.
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
      and uncertainty on both physical and mental health. From primary healthcare services to mental
      health counseling, legal support, and health literacy workshops, we are committed to providing
      the necessary tools for individuals to build healthier, more stable lives in Australia.
    </p>
  </div>
  <div class="mission">
    <h1 id="misson">Our Mission</h1>
    <p>
      At the heart of our work is a commitment to ensuring equitable healthcare access for
      underrepresented communities. No one should have to struggle to receive basic medical
      attention simply because of their circumstances. We strive to eliminate the barriers refugees
      and asylum seekers face, offering not only direct medical support but also the education and
      advocacy needed to drive long-term change.
    </p>
    <p>
      Our mission extends beyond individual care—it’s about fostering systemic improvements. We work
      alongside healthcare providers, policymakers, and community leaders to push for better
      healthcare protections for refugees and asylum seekers. By raising awareness and advocating
      for inclusive policies, we aim to ensure these communities receive the recognition and support
      they deserve.
    </p>
    <p>
      Through our outreach programs, medical referrals, and health workshops, we are helping
      individuals not only access treatment but also gain the confidence to advocate for their own
      health needs. Every person deserves compassionate care, and we are dedicated to making that a
      reality.
    </p>
  </div>
  <div class="team">
    <h1 id="team">Team</h1>
    <p>
      Our team is made up of dedicated professionals, volunteers, and advocates who share a passion
      for health equity and social justice. From medical experts providing direct care to community
      outreach coordinators ensuring individuals receive necessary support, every member of our team
      plays a critical role in strengthening the health and well-being of refugees and asylum
      seekers.
    </p>
    <p>
      Our healthcare professionals specialize in refugee health, mental health support, and
      culturally competent care, ensuring that every individual who seeks help from our organization
      receives appropriate and effective treatment. Alongside them, our educators and policy
      advocates work tirelessly to provide health literacy training and push for systemic
      improvements that will create lasting change.
    </p>
    <p>
      Beyond our core staff, we rely on the support of a dedicated volunteer network, which includes
      individuals assisting with mentorship programs, event coordination, and administrative work.
      Whether offering medical expertise or simply providing a welcoming, supportive environment,
      our team is united by a commitment to making healthcare more accessible and equitable.
    </p>
  </div>
  <div class="links">
    <h1 id="links">Partner Organisations</h1>
    <p>
      Collaboration is central to our success. We work closely with a diverse range of partner
      organizations, from healthcare providers and advocacy groups to universities and government
      agencies, all of whom share our commitment to improving health outcomes for refugees and
      asylum seekers.
    </p>
    <p>
      These partnerships allow us to expand our reach and ensure comprehensive support for the
      individuals we serve. By joining forces with medical institutions, we provide direct
      healthcare referrals and specialized treatment options. Our relationships with community
      organizations enable us to offer social support services such as housing assistance and
      employment programs.
    </p>
    <p>
      Meanwhile, our collaboration with policymakers allows us to advocate for legislative changes
      that will improve long-term healthcare access. Every partnership strengthens our ability to
      support those in need. Together, we are building a network of care that ensures refugees and
      asylum seekers in Australia receive the medical attention, education, and resources they need
      to live healthier lives.
    </p>
    <p>
      We are grateful for the support of our partners and the impact they have on our mission. If
      you are interested in collaborating with us or learning more about our partner organizations,
      please reach out to us.
    </p>
    <ul class="my-2">
      <li>
        <strong>Refugee Council of Australia</strong> – Advocating for the rights and welfare of
        refugees and asylum seekers.
      </li>
      <li>
        <strong>Australian Red Cross</strong> – Providing humanitarian support and emergency
        assistance.
      </li>
      <li>
        <strong>Multicultural Health and Support Service</strong> – Offering culturally appropriate
        healthcare services.
      </li>
      <li>
        <strong>Settlement Services International</strong> – Supporting refugees in their settlement
        journey.
      </li>
      <li>
        <strong>Refugee Health Network</strong> – Promoting health and well-being for refugees and
        asylum seekers.
      </li>
    </ul>
  </div>
  <div class="contact my-5">
    <h1 id="contact" class="text-center my-5">Contact Us</h1>
    <div class="row">
      <div class="col-md-6">
        <div>
          <strong>Opening Hours</strong>
          <p>Monday to Friday: 9am - 5pm</p>
        </div>
        <div>
          <strong>Phone Number</strong>
          <p>+61 2 1234 5678</p>
        </div>
        <div>
          <strong>Address</strong>
          <p>123 High Street, Sydney, NSW 2000</p>
        </div>
        <div>
          <iframe
            width="100%"
            height="330"
            loading="lazy"
            crossorigin="anonymous"
            marginheight="0"
            marginwidth="0"
            src="https://maps.google.com/maps?width=100%25&amp;height=330&amp;hl=en&amp;q=123%20High%20Street,%20Sydney,%20NSW%202000+()
              &amp;t=&amp;z=17&amp;ie=UTF8&amp;iwloc=B&amp;output=embed"
          ></iframe>
        </div>
      </div>
      <div class="row col-md-6">
        <p>
          <strong>
            Whether you want to volunteer, donate, inquire or just want to say hello, we would love
            to hear from you, send us a message.
          </strong>
        </p>
        <form @submit.prevent="submitForm" class="mb-5">
          <div class="row mb-3">
            <div class="col-6 col-md-6">
              <label for="firstname" class="form-label">First Name</label>
              <input
                type="text"
                class="form-control"
                id="firstname"
                @blur="() => validateName(true)"
                @input="() => validateName(false)"
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
                @blur="() => validateName(true)"
                @input="() => validateName(false)"
                v-model="formData.surname"
              />
              <div v-if="errors.surname" class="text-danger">{{ errors.surname }}</div>
            </div>
          </div>
          <div class="row mb-3">
            <div class="col-6 col-md-6">
              <label for="email" class="form-label">Email</label>
              <input
                type="text"
                class="form-control"
                id="email"
                @blur="() => validateEmail(true)"
                @input="() => validateEmail(false)"
                v-model="formData.email"
              />
              <div v-if="errors.email" class="text-danger">{{ errors.email }}</div>
            </div>
            <div class="col-6 col-md-6">
              <label for="phone" class="form-label">Phone</label>
              <input
                type="text"
                class="form-control"
                id="phone"
                @blur="() => validatePhone(true)"
                @input="() => validatePhone(false)"
                v-model="formData.phone"
              />
              <div v-if="errors.phone" class="text-danger">{{ errors.phone }}</div>
            </div>
          </div>
          <div class="col-12 col-md-12">
            <div class="subject">
              <label for="subject" class="form-label">Subject</label>
              <select
                class="form-select"
                id="subject"
                @blur="() => validateSubject(true)"
                @input="() => validateSubject(false)"
                v-model="formData.subject"
              >
                <option value="volunteer">Volunteer</option>
                <option value="donate">Donate</option>
                <option value="enquire">Enquire</option>
                <option value="general">General</option>
              </select>
              <div v-if="errors.subject" class="text-danger">{{ errors.subject }}</div>
            </div>
          </div>
          <div class="mb-3">
            <label for="userMessage" class="form-label">Message</label>
            <textarea
              class="form-control"
              id="userMessage"
              rows="7"
              @blur="() => validateMessage(true)"
              @input="() => validateMessage(false)"
              v-model="formData.usermessage"
            ></textarea>
            <div v-if="errors.usermessage" class="test-danger">
              {{ errors.usermessage }}
            </div>
          </div>
          <div class="text-center">
            <button type="submit" class="btn btn-primary me-2">Submit</button>
            <button type="button" class="btn btn-secondary" @click="clearForm">Clear</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import db from '../firebase/init.js'
import { collection, addDoc } from 'firebase/firestore'

const formData = ref({
  firstname: '',
  surname: '',
  email: '',
  phone: '',
  subject: '',
  usermessage: '',
})

const submitForm = async () => {
  try {
    validateName(true)
    if (
      !errors.value.firstname &&
      !errors.value.surname &&
      !errors.value.email &&
      !errors.value.phone &&
      !errors.value.subject &&
      !errors.value.usermessage
    ) {
      await addDoc(collection(db, 'contact'), {
        firstname: formData.value.firstname,
        surname: formData.value.surname,
        email: formData.value.email,
        phone: formData.value.phone,
        subject: formData.value.subject,
        usermessage: formData.value.usermessage,
      })
      alert('Form submitted successfully!')
    }
  } catch (error) {
    console.error('Error submitting form:', error)
  } finally {
    formData.value = {
      firstname: '',
      surname: '',
      email: '',
      phone: '',
      subject: '',
      usermessage: '',
    }
    clearForm()
  }
}

const clearForm = () => {
  formData.value = {
    firstname: '',
    surname: '',
    email: '',
    phone: '',
    subject: '',
    usermessage: '',
  }
}

const errors = ref({
  firstname: null,
  surname: null,
  email: null,
  phone: null,
  subject: null,
  usermessage: null,
})

const validateName = (blur) => {
  if (formData.value.firstname.length < 3) {
    if (blur) errors.value.firstname = 'First name must be at least 3 characters'
  } else if (formData.value.surname.length < 3) {
    if (blur) errors.value.surname = 'Surname must be at least 3 characters'
  } else {
    errors.value.firstname = null
    errors.value.surname = null
  }
}

const validateSubject = (blur) => {
  if (formData.value.subject === '') {
    if (blur) errors.value.subject = 'Subject is required'
  } else {
    errors.value.usermessage = null
  }
}

const validateEmail = (blur) => {
  const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/
  if (!emailPattern.test(formData.value.email)) {
    if (blur) errors.value.email = 'Not a vaild email address'
  } else {
    errors.value.email = null
  }
}

const validatePhone = (blur) => {
  const phonePattern = /^\d{10}$/
  if (!phonePattern.test(formData.value.phone)) {
    if (blur) errors.value.phone = 'Phone number must be 10 digits'
  } else {
    errors.value.phone = null
  }
}

const validateMessage = (blur) => {
  if (formData.value.usermessage.length < 10) {
    if (blur) errors.value.usermessage = 'Message must be at least 10 characters'
    else {
      errors.value.usermessage = null
    }
  }
}
</script>

<style scoped>
h1 {
  text-align: center;
}
</style>
