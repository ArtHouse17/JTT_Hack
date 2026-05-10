import { Checkbox, SegmentedRadioGroup } from '@gravity-ui/uikit'
import { useState } from 'react'
import { useQuery } from '@tanstack/react-query'
import { getTasks } from '../api/tasks'
import { TaskTest } from './TaskTest'
import { TaskMistakes } from './TaskMistakes'
import { TaskOpen } from './TaskOpen'

export function TasksPage() {
  const [type, setType] = useState<'test' | 'mistakes' | 'open'>('test')
  const [onlyUnsolved, setOnlyUnsolved] = useState(false)

  const { data: tasks, refetch } = useQuery({
    queryKey: ['tasks', type],
    queryFn: () => getTasks(type),
  })

  const filteredTasks = tasks?.filter((task) => !onlyUnsolved || !task.solved)

  return (
    <>
      <div className="flex items-center justify-between">
        <SegmentedRadioGroup
          options={[
            { content: 'Тестовые', value: 'test' },
            { content: 'Поиск ошибок', value: 'mistakes' },
            { content: 'Открытые', value: 'open' },
          ]}
          onChange={(event) => setType(event.target.value as typeof type)}
          value={type}
        />
        <Checkbox
          checked={onlyUnsolved}
          onChange={(event) => {
            setOnlyUnsolved(event.target.checked)
            refetch()
          }}
          content="Только нерешённые"
        />
      </div>

      {filteredTasks && (
        <div className="my-6 flex flex-col gap-6 divide-y divide-gray-200">
          {filteredTasks.map((task) => {
            switch (task.type) {
              case 'test':
                return <TaskTest task={task} key={task.id} />
              case 'mistakes':
                return <TaskMistakes task={task} key={task.id} />
              case 'open':
                return <TaskOpen task={task} key={task.id} />
            }
          })}
        </div>
      )}
    </>
  )
}
