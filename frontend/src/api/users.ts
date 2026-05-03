export type UserInfo = {
  username: string
}

/** GET /users/me */
export async function getUserInfo(): Promise<UserInfo> {
  const response = await fetch(`${import.meta.env.VITE_BACKEND_URL}/users/me`, {
    credentials: 'include',
  })
  if (response.status === 401) {
    if (window.location.pathname !== '/login') {
      window.location.href = '/login'
    } else {
      throw new Error('Ошибка авторизации')
    }
  }
  return await response.json()
}
