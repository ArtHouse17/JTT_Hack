import { useState, useEffect } from 'react'
import { useNavigate } from 'react-router'
import { Button, PasswordInput, SegmentedRadioGroup, TextInput } from '@gravity-ui/uikit'
import { login, signup } from '../api/auth'
import { getUserInfo } from '../api/users'
import { useQuery } from '@tanstack/react-query'

export function LoginPage() {
  const navigate = useNavigate()
  const { data: userInfo } = useQuery({ queryKey: ['userInfo'], queryFn: getUserInfo })
  const [formType, setFormType] = useState<'login' | 'signup'>('login')
  const [username, setUsername] = useState('')
  const [password, setPassword] = useState('')
  const [passwordAgain, setPasswordAgain] = useState('')

  useEffect(() => {
    if (userInfo) navigate('/')
  }, [userInfo, navigate])

  function isUsernameValid() {
    return username.length >= 1
  }

  function isPasswordValid() {
    return password.length >= 1
  }

  function isPasswordAgainValid() {
    return password === passwordAgain
  }

  const isLoginFormValid = isUsernameValid() && isPasswordValid()
  const isSignupFormValid = isLoginFormValid && isPasswordAgainValid()

  async function handleLoginSubmit(event: React.FormEvent<HTMLFormElement>) {
    event.preventDefault()
    try {
      await login({ username, password })
      navigate('/')
    } catch {
      alert('Ошибка входа')
    }
  }

  async function handleSignupSubmit(event: React.FormEvent<HTMLFormElement>) {
    event.preventDefault()
    try {
      await signup({ username, password })
      navigate('/')
    } catch {
      alert('Ошибка регистрации')
    }
  }

  function handleUsernameChange(event: React.ChangeEvent<HTMLInputElement, Element>) {
    setUsername(event.target.value.replace(/\s/g, ''))
  }

  function handlePasswordChange(event: React.ChangeEvent<HTMLInputElement, Element>) {
    setPassword(event.target.value.replace(/\s/g, ''))
  }

  function handlePasswordAgainChange(event: React.ChangeEvent<HTMLInputElement, Element>) {
    setPasswordAgain(event.target.value.replace(/\s/g, ''))
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
              value={username}
              onChange={handleUsernameChange}
            />
            <PasswordInput
              placeholder="Пароль"
              value={password}
              onChange={handlePasswordChange}
            />
            <Button type="submit" view="action" disabled={!isLoginFormValid}>
              Войти
            </Button>
          </form>
        ) : (
          <form onSubmit={handleSignupSubmit} className="flex flex-col gap-3">
            <TextInput
              placeholder="Логин"
              value={username}
              onChange={handleUsernameChange}
            />
            <PasswordInput
              placeholder="Пароль"
              value={password}
              onChange={handlePasswordChange}
            />
            <PasswordInput
              placeholder="Пароль ещё раз"
              value={passwordAgain}
              onChange={handlePasswordAgainChange}
            />
            <Button type="submit" view="action" disabled={!isSignupFormValid}>
              Создать аккаунт
            </Button>
          </form>
        )}
      </div>
    </div>
  )
}
