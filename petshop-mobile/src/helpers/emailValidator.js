export function emailValidator(email) {
  const re = /\S+@\S+\.\S+/
  if (!email) return "Email alanı boş olamaz."
  if (!re.test(email)) return 'Ooops! Doğru bir email adresine ihtiyacımız var.'
  return ''
}
