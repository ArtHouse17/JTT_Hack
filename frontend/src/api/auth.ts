type LoginRequest = {
  username: string
  password: string
}

type SignupRequest = {
  username: string
  password: string
}

export async function login(credentials: LoginRequest): Promise<void> {
  const response = await fetch(`${import.meta.env.VITE_BACKEND_URL}/auth/login`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    credentials: 'include',
    body: JSON.stringify(credentials),
  })
  if (!response.ok) {
    throw new Error('Ошибка при входе')
  }
}

export async function signup(credentials: SignupRequest): Promise<void> {
  const response = await fetch(`${import.meta.env.VITE_BACKEND_URL}/auth/signup`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    credentials: 'include',
    body: JSON.stringify(credentials),
  })
  if (!response.ok) {
    throw new Error('Ошибка при регистрации')
  }
}

export async function logout(): Promise<void> {
  const response = await fetch(`${import.meta.env.VITE_BACKEND_URL}/auth/logout`, {
    method: 'POST',
    credentials: 'include',
  })
  if (response.status === 401) {
    window.location.href = '/login'
  }
}
