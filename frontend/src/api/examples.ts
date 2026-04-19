import type { TestTask, MistakesTask, OpenTask } from './tasks'
import type { UserInfo } from './users'
import type { TaskAttemptResponse } from './attempts'
import type { Progress } from './progress'

export const TASK_ATTEMPT_RESPONSE_EXAMPLE: TaskAttemptResponse = {
  correct: true,
}

export const USER_INFO_EXAMPLE: UserInfo = { username: 'la_sima' }

export const TEST_TASKS_EXAMPLE: TestTask[] = [
  {
    id: 'c044082b-b0da-4998-b3bd-9ba2217668c4',
    type: 'test',
    points: 1,
    multiple: false,
    solved: false,
    question: 'Как расшифровывается аббревиатура SQL?',
    options: [
      { id: 'ebcd3eb4-9b40-476d-865f-48c6ceb97f14', text: 'Structured Query Language' },
      { id: '050d5eaf-d00d-402e-9cfd-aa85a7e52c64', text: 'Structured Questionable Logic' },
      { id: 'b15cc70f-828e-46f4-94eb-8df450dec83c', text: 'Slightly Quirky Language' },
      { id: '3f2c4ab3-da69-4b0c-8df9-a0863e5999e1', text: 'Simple Query Language' },
    ],
  },
  {
    id: 'c044082b-b0da-4998-b3bd-9ba2217668c4',
    type: 'test',
    points: 1,
    multiple: true,
    solved: false,
    question: 'Как расшифровывается аббревиатура SQL?',
    options: [
      { id: 'ebcd3eb4-9b40-476d-865f-48c6ceb97f14', text: 'Structured Query Language' },
      { id: '050d5eaf-d00d-402e-9cfd-aa85a7e52c64', text: 'Structured Questionable Logic' },
      { id: 'b15cc70f-828e-46f4-94eb-8df450dec83c', text: 'Slightly Quirky Language' },
      { id: '3f2c4ab3-da69-4b0c-8df9-a0863e5999e1', text: 'Simple Query Language' },
    ],
  },
]

export const MISTAKES_TASKS_EXAMPLE: MistakesTask[] = [
  {
    id: 'ebcd3eb4-9b40-476d-865f-48c6ceb97f14',
    type: 'mistakes',
    points: 2,
    solved: false,
    question: 'Исправьте ошибку',
    answer: 'Ошыбка',
  },
]

export const OPEN_TASKS_EXAMPLE: OpenTask[] = [
  {
    id: 'ebcd3eb4-9b40-476d-865f-48c6ceb97f14',
    type: 'open',
    points: 3,
    code: true,
    solved: false,
    question: `**Таблица:** \`Employee\`

| Column Name | Type |
| :--- | :--- |
| \`id\` | int |
| \`name\` | varchar |
| \`salary\` | int |

\`id\` — первичный ключ.

Напишите SQL-запрос, чтобы найти имена (\`name\`) всех сотрудников, чья зарплата **строго больше**, чем средняя зарплата всех сотрудников в таблице.

**Пример:**
**Входные данные:**
| id | name | salary |
| :--- | :--- | :--- |
| 1 | Alice | 100 |
| 2 | Bob | 200 |
| 3 | Charlie | 300 |

**Выходные данные:**
| name |
| :--- |
| Charlie |

*Объяснение: Средняя зарплата = 200. Только у Charlie зарплата 300 > 200.*`,
    answer: 'select * from Employee;',
  },
]

export const PROGRESS_EXAMPLE: Progress = {
  pointsTotal: 30,
  pointsEarned: 12,
  testTasksTotal: 5,
  testTasksSolved: 3,
  mistakesTasksTotal: 4,
  mistakesTasksSolved: 2,
  openTasksTotal: 6,
  openTasksSolved: 1,
}
