export type Progress = {
  /** Максимально возможное количество баллов */
  pointsTotal: number
  /** Суммарное количество баллов пользователя */
  pointsEarned: number
  /** Всего тестовых заданий */
  testTasksTotal: number
  /** Решено тестовых заданий */
  testTasksSolved: number
  /** Всего заданий на поиск ошибок */
  mistakesTasksTotal: number
  /** Решено заданий на поиск ошибок */
  mistakesTasksSolved: number
  /** Всего открытых заданий */
  openTasksTotal: number
  /** Решено открытых заданий */
  openTasksSolved: number
}

export async function getProgress(): Promise<Progress> {
  const response = await fetch(`${import.meta.env.VITE_BACKEND_URL}/users/me/progress`, {
    credentials: 'include',
  })
  if (response.status === 401) {
    window.location.href = '/login'
  }
  return await response.json()
}

export async function resetProgress(): Promise<void> {
  const response = await fetch(`${import.meta.env.VITE_BACKEND_URL}/users/me/progress`, {
    method: 'DELETE',
    credentials: 'include',
  })
  if (response.status === 401) {
    window.location.href = '/login'
  }
}
