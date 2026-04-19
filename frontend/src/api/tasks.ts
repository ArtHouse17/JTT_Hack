import { TEST_TASKS_EXAMPLE, MISTAKES_TASKS_EXAMPLE, OPEN_TASKS_EXAMPLE } from './examples'

export type TaskType = 'test' | 'mistakes' | 'open'

export type TestTask = {
  id: string
  type: Extract<TaskType, 'test'>
  points: number
  /** Можно ли выбирать несколько вариантов ответа */
  multiple: boolean
  /** Была ли когда-нибудь удачная попытка решения */
  solved: boolean
  /** Текст вопроса */
  question: string
  /** Варианты ответа */
  options: {
    id: string
    /** Сам текст варианта ответа */
    text: string
  }[]
}

export type MistakesTask = {
  id: string
  type: Extract<TaskType, 'mistakes'>
  points: number
  /** Была ли когда-нибудь удачная попытка решения */
  solved: boolean
  /** Текст вопроса */
  question: string
  /** Текст с ошибками, которые нужно будет исправить */
  answer: string
}

export type OpenTask = {
  id: string
  type: Extract<TaskType, 'open'>
  points: number
  /** Будет ли результат запускаться как SQL-код */
  code: boolean
  /** Была ли когда-нибудь удачная попытка решения */
  solved: boolean
  /** Текст вопроса */
  question: string
  /** Значение по умолчанию в поле ответа */
  answer: string
}

export type Tasks = TestTask[] | OpenTask[] | MistakesTask[]

/** GET /tasks?type={test|mistakes|open} */
export async function getTasks(type: TaskType): Promise<Tasks> {
  switch (type) {
    case 'test':
      return TEST_TASKS_EXAMPLE
    case 'mistakes':
      return MISTAKES_TASKS_EXAMPLE
    case 'open':
      return OPEN_TASKS_EXAMPLE
  }
}
