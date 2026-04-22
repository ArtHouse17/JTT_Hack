import { PROGRESS_EXAMPLE } from './examples'

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

export async function getProgress() {
  return PROGRESS_EXAMPLE
}
