import { useState } from 'react'
import { Button, PasswordInput, SegmentedRadioGroup, TextInput } from '@gravity-ui/uikit'

export function LoginPage() {
  const [formType, setFormType] = useState<'login' | 'signup'>('login')
  const [username, setUsername] = useState('')
  const [password, setPassword] = useState('')
  const [passwordAgain, setPasswordAgain] = useState('')
  const [showValidationErrors, setShowValidationErrors] = useState(false)

  const usernameErrorMessage = 'От 4 до 20 символов, латиница и _'
  function getUsernameValidationState() {
    if (!showValidationErrors) return
    if (username.length < 4 || username.length > 40 || !/^[A-Za-z_]+$/.test(username))
      return 'invalid'
  }

  const passwordErrorMessage = 'От 8 до 30 символов, минимум 1 буква и 1 цифра'
  function getPasswordValidationState() {
    if (!showValidationErrors) return
    if (
      password.length < 8 ||
      password.length > 30 ||
      !/[a-zA-Z]/.test(password) ||
      !/[0-9]/.test(password)
    )
      return 'invalid'
  }

  const passwordAgainErrorMessage = 'Пароли не совпадают'
  function getPasswordAgainValidationState() {
    if (!showValidationErrors) return
    if (passwordAgain && password !== passwordAgain) return 'invalid'
  }

  function handleLoginSubmit(event: React.SubmitEvent<HTMLFormElement>) {
    event.preventDefault()
  }

  function handleSignupSubmit(event: React.SubmitEvent<HTMLFormElement>) {
    event.preventDefault()
  }

  function handleUsernameChange(event: React.ChangeEvent<HTMLInputElement, Element>) {
    setUsername(event.target.value)
    setShowValidationErrors(false)
  }

  function handlePasswordChange(event: React.ChangeEvent<HTMLInputElement, Element>) {
    setPassword(event.target.value)
    setShowValidationErrors(false)
  }

  function handlePasswordAgainChange(event: React.ChangeEvent<HTMLInputElement, Element>) {
    setPasswordAgain(event.target.value)
    setShowValidationErrors(false)
  }

  return (
    <div className="flex h-dvh items-center justify-center">
      <div className="flex w-80 flex-col gap-3">
        <SegmentedRadioGroup
          width="max"
          options={[
            { content: 'Вход', value: 'login' },
            { content: 'Регистрация', value: 'signup' },
          ]}
          onChange={(event) => setFormType(event.target.value as typeof formType)}
          value={formType}
        />

        {formType === 'login' ? (
          <form onSubmit={handleLoginSubmit} className="flex flex-col gap-3">
            <TextInput
              placeholder="Логин"
              validationState={getUsernameValidationState()}
              value={username}
              onChange={handleUsernameChange}
              errorMessage={usernameErrorMessage}
            />
            <PasswordInput
              placeholder="Пароль"
              validationState={getPasswordValidationState()}
              value={password}
              onChange={handlePasswordChange}
              errorMessage={passwordErrorMessage}
            />
            <Button type="submit" view="action">
              Войти
            </Button>
          </form>
        ) : (
          <form onSubmit={handleSignupSubmit} className="flex flex-col gap-3">
            <TextInput
              placeholder="Логин"
              validationState={getUsernameValidationState()}
              value={username}
              onChange={handleUsernameChange}
              errorMessage={usernameErrorMessage}
            />
            <PasswordInput
              placeholder="Пароль"
              validationState={getPasswordValidationState()}
              value={password}
              onChange={handlePasswordChange}
              errorMessage={passwordErrorMessage}
            />
            <PasswordInput
              placeholder="Пароль ещё раз"
              validationState={getPasswordAgainValidationState()}
              value={passwordAgain}
              onChange={handlePasswordAgainChange}
              errorMessage={passwordAgainErrorMessage}
            />
            <Button type="submit" view="action">
              Создать аккаунт
            </Button>
          </form>
        )}
      </div>
    </div>
  )
}
