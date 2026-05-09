import { Check, Xmark } from '@gravity-ui/icons'
import { Button, Icon, Label, Text } from '@gravity-ui/uikit'
import { useMutation, useQueryClient } from '@tanstack/react-query'
import { useState } from 'react'
import type { TaskAttemptRequest, TaskAttemptResponse } from '../api/attempts'
import { postTaskAttempt } from '../api/attempts'
import type { OpenTask } from '../api/tasks'
import { Editor } from '@monaco-editor/react'
import { marked } from 'marked'
import { formatPoints } from '../utils'

export function TaskOpen({ task }: { task: OpenTask }) {
  const [answer, setAnswer] = useState(task.answer)
  const [showResult, setShowResult] = useState<boolean>(false)
  const [solved, setSolved] = useState(task.solved)
  const [prevAnswer, setPrevAnswer] = useState<string | null>(null)
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

  function handleSubmit(event: React.FormEvent<HTMLFormElement>) {
    event.preventDefault()
    mutation.mutate({ answer })
    setPrevAnswer(answer)
  }

  function handleReset() {
    setAnswer(task.answer)
    setShowResult(false)
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

      <div
        className="whitespace-pre-wrap [&_table]:border-collapse [&_table]:border [&_td]:border [&_td]:p-1 [&_th]:border [&_th]:p-1"
        dangerouslySetInnerHTML={{ __html: marked.parse(task.question) }}
      ></div>

      <Editor
        className="mt-4 border border-gray-300"
        height={200}
        defaultLanguage="sql"
        value={answer}
        onChange={(value) => {
          setAnswer(value ?? '')
          setShowResult(false)
        }}
        options={{ minimap: { enabled: false } }}
        loading="Загрузка..."
      />

      <div className="mt-3.5 flex items-center gap-4">
        <Button
          type="submit"
          view="action"
          disabled={answer.trim() === prevAnswer?.trim() || !answer.trim()}
        >
          Отправить
        </Button>

        <Button type="button" onClick={handleReset} disabled={answer === task.answer}>
          Сбросить
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
