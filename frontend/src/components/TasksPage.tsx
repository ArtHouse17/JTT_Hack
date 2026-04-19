import { SegmentedRadioGroup } from '@gravity-ui/uikit'
import { useState } from 'react'
import { useQuery } from '@tanstack/react-query'
import { getTasks } from '../api/tasks'
import { TaskTest } from './TaskTest'
import { TaskMistakes } from './TaskMistakes'
import { TaskOpen } from './TaskOpen'

export function TasksPage() {
  const [type, setType] = useState<'test' | 'mistakes' | 'open'>('test')

  const { data: tasks } = useQuery({
    queryKey: ['tasks', type],
    queryFn: () => getTasks(type),
  })

  return (
    <>
      <SegmentedRadioGroup
        options={[
          { content: 'Тестовые', value: 'test' },
          { content: 'Поиск ошибок', value: 'mistakes' },
          { content: 'Открытые', value: 'open' },
        ]}
        onChange={(event) => setType(event.target.value as typeof type)}
        value={type}
      />

      {tasks && (
        <div className="my-6 flex flex-col gap-6">
          {tasks.map((task) => {
            switch (task.type) {
              case 'test':
                return <TaskTest task={task} />
              case 'mistakes':
                return <TaskMistakes task={task} />
              case 'open':
                return <TaskOpen task={task} />
            }
          })}
        </div>
      )}
    </>
  )
}
