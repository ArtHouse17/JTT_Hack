import { TASK_ATTEMPT_RESPONSE_EXAMPLE } from './examples'

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
}

/** POST /tasks/{taskId}/attempts */
export async function postTaskAttempt(
  taskId: string,
  answer: TaskAttemptRequest,
): Promise<TaskAttemptResponse> {
  console.log(taskId, answer)
  return TASK_ATTEMPT_RESPONSE_EXAMPLE
}
