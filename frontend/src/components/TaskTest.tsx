import { Check, Xmark } from '@gravity-ui/icons'
import { Button, Checkbox, Icon, Label, Radio, Text } from '@gravity-ui/uikit'
import { useMutation, useQueryClient } from '@tanstack/react-query'
import { useState } from 'react'
import type { TaskAttemptResponse, TaskAttemptRequest } from '../api/attempts'
import { postTaskAttempt } from '../api/attempts'
import type { TestTask } from '../api/tasks'
import { formatPoints } from '../utils'

export function TaskTest({ task }: { task: TestTask }) {
  const [answer, setAnswer] = useState<string[]>([])
  const [showResult, setShowResult] = useState<boolean>(false)
  const [solved, setSolved] = useState(task.solved)
  const queryClient = useQueryClient()

  const mutation = useMutation<TaskAttemptResponse, Error, TaskAttemptRequest>({
    mutationFn: (data) => postTaskAttempt(task.id, data),
    onSuccess: (data) => {
      setShowResult(true)
      if (data.correct) {
        setSolved(true)
        queryClient.invalidateQueries({ queryKey: ['tasks'] })
      }
    },
  })

  function handleSubmit(event: React.SubmitEvent<HTMLFormElement>) {
    event.preventDefault()
    if (answer.length === 0) return
    mutation.mutate({ answer: answer })
  }

  function handleChange(checked: boolean, optionId: string) {
    setShowResult(false)

    if (task.multiple) {
      setAnswer((prev) => (checked ? [...prev, optionId] : prev.filter((id) => id !== optionId)))
    } else {
      if (checked) {
        setAnswer([optionId])
      }
    }
  }

  return (
    <form onSubmit={handleSubmit} className="pb-6">
      <div className="mb-2 flex items-center gap-2 text-[11px]">
        <Text color="secondary">{formatPoints(task.points)}</Text>
        <span className="text-gray-400">•</span>
        {solved ? (
          <Label theme="success" className="text-[11px]">
            Решено
          </Label>
        ) : (
          <Label theme="normal" className="text-[11px]">
            Не решено
          </Label>
        )}
      </div>

      <Text>{task.question}</Text>

      <ul className="mt-2">
        {task.options.map((option) => (
          <li key={option.id}>
            {task.multiple ? (
              <Checkbox
                value={option.id}
                className="my-1"
                content={option.text}
                checked={answer.includes(option.id)}
                onChange={(event) => handleChange(event.target.checked, option.id)}
              />
            ) : (
              <Radio
                value={option.id}
                className="py-1"
                content={option.text}
                checked={answer.includes(option.id)}
                onChange={(event) => handleChange(event.target.checked, option.id)}
              />
            )}
          </li>
        ))}
      </ul>

      <div className="mt-2 flex items-center gap-4">
        <Button type="submit" view="action" disabled={answer.length === 0 || showResult}>
          Отправить
        </Button>
        {mutation.isSuccess && showResult && (
          <div className="flex items-center gap-1">
            {mutation.data.correct ? (
              <>
                <Icon data={Check} color="positive" />
                <Text color="positive">Правильно</Text>
              </>
            ) : (
              <>
                <Icon data={Xmark} color="danger" />
                <Text color="danger">Неправильно</Text>
              </>
            )}
          </div>
        )}
      </div>
    </form>
  )
}
