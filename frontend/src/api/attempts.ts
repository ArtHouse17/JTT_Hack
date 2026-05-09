export type TaskAttemptRequest = {
  /**
   * Ответ пользователя.
   * Для открытых вопросов и вопросов на поиск ошибок: строка,
   * Для тестовых задания: массив ID выбранных опций
   */
  answer: string | string[]
}

export type TaskAttemptResponse = {
  /** Правильный ответ или нет */
  correct: boolean
  /** Код SQL-ошибки, если запрос не выполнился */
  errorCode?: string | null
  /** Короткий текст SQL-ошибки для пользователя */
  errorMessage?: string | null
}

/** POST /tasks/{taskId}/attempts */
export async function postTaskAttempt(
  taskId: string,
  request: TaskAttemptRequest,
): Promise<TaskAttemptResponse> {
  const response = await fetch(`${import.meta.env.VITE_BACKEND_URL}/tasks/${taskId}/attempts`, {
    credentials: 'include',
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(request),
  })
  if (response.status === 401) {
    window.location.href = '/login'
  }
  return await response.json()
}
