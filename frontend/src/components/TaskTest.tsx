import { Check, Xmark } from '@gravity-ui/icons'
import { Button, Checkbox, Icon, Label, Radio, Text } from '@gravity-ui/uikit'
import { useMutation } from '@tanstack/react-query'
import { useState } from 'react'
import type { TaskAttemptResponse, TaskAttemptRequest } from '../api/attempts'
import { postTaskAttempt } from '../api/attempts'
import type { TestTask } from '../api/tasks'

export function TaskTest({ task }: { task: TestTask }) {
  const [answer, setAnswer] = useState<string[]>([])
  const [showResult, setShowResult] = useState<boolean>(false)
  const [solved, setSolved] = useState(task.solved)

  const mutation = useMutation<TaskAttemptResponse, Error, TaskAttemptRequest>({
    mutationFn: (data) => postTaskAttempt(task.id, data),
    onSuccess: (data) => {
      setShowResult(true)
      if (data.correct) {
        setSolved(true)
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
    <form onSubmit={handleSubmit}>
      <Text>{task.question}</Text>

      {solved && (
        <Label theme="success" className="ml-2">
          Решено
        </Label>
      )}

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
                <Icon data={Xmark} color="positive" />
                <Text color="positive">Неправильно</Text>
              </>
            )}
          </div>
        )}
      </div>
    </form>
  )
}
