// Validation functions for various form data

// Email validation
export const validateEmail = (email, errors, blur) => {
  const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/
  if (!emailPattern.test(email)) {
    if (blur) {
      errors.email = 'Not a vaild email address'
    }
  } else {
    errors.email = null
  }
}

// Password validation
export const validatePassword = (password, errors, blur) => {
  const minLength = 8
  const hasUpperCase = /[A-Z]/.test(password)
  const hasLowerCase = /[a-z]/.test(password)
  const hasNumber = /\d/.test(password)
  const hasSpecialChar = /[!@#$%^&*(),.?":{}|<>]/.test(password)

  if (password.length < minLength) {
    if (blur) errors.password = `Password must be at least ${minLength} characters long`
  } else if (!hasUpperCase) {
    if (blur) errors.password = 'Password must contain at least one uppercase letter'
  } else if (!hasLowerCase) {
    if (blur) errors.password = 'Password must contain at least one lowercase letter'
  } else if (!hasNumber) {
    if (blur) errors.password = 'Password must contain at least one number'
  } else if (!hasSpecialChar) {
    if (blur) errors.password = 'Password must contain at least one special character'
  } else {
    errors.password = null
  }
}

// Confirm password validation
export const validateConfirmPassword = (password, confirmPassword, errors, blur) => {
  if (password !== confirmPassword) {
    if (blur) errors.confirmPassword = 'Passwords do not match.'
  } else {
    errors.confirmPassword = null
  }
}

// Name validation
export const validateName = (name, fieldName, errors, blur) => {
  if (name.trim().length < 3) {
    if (blur)
      errors[fieldName] =
        `${fieldName.charAt(0).toUpperCase() + fieldName.slice(1)} must be at least 3 characters`
  } else {
    errors[fieldName] = null
  }
}

// Gender validation
export const validateGender = (gender, errors, blur) => {
  if (gender === '') {
    if (blur) errors.gender = 'gender is required'
  } else {
    errors.gender = null
  }
}

// Date of birth validation
export const validateDob = (dob, errors, blur) => {
  if (!dob) {
    if (blur) errors.dob = 'Date of birth is required'
  } else {
    errors.dob = null
  }
}

// Phone number validation
export const validatePhone = (phone, errors, blur) => {
  const phonePattern = /^\d{10}$/
  if (!phonePattern.test(phone)) {
    if (blur) errors.phone = 'Phone number must be 10 digits'
  } else {
    errors.phone = null
  }
}

// Subject of message validation
export const validateSubject = (subject, errors, blur) => {
  if (blur) {
    if (!subject) {
      errors.subject = 'Please select a subject'
    } else {
      errors.subject = null
    }
  }
}

// Message validation
export const validateMessage = (usermessage, errors, blur) => {
  if (usermessage.length < 10) {
    if (blur) errors.usermessage = 'Message must be at least 10 characters'
    else {
      errors.usermessage = null
    }
  }
}
