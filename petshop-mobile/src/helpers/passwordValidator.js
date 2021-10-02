export function passwordValidator(password) {
  if (!password) return "Şifre alanı boş olamaz."
  if (password.length < 4) return 'Şifre en az 5 karakter uzunluğunda olmalı.'
  return ''
}
