package br.com.alex.springAPI.application.usecases;

import br.com.alex.springAPI.domain.valueObjects.WorkoutDetail;
import br.com.alex.springAPI.application.input.PageRequestApplication;
import br.com.alex.springAPI.application.output.ExerciseOutput;
import br.com.alex.springAPI.application.output.WorkoutDetailOutput;
import br.com.alex.springAPI.domain.Exercise;
import br.com.alex.springAPI.domain.interfaces.IWorkoutRepository;
import br.com.alex.springAPI.domain.valueObjects.Pagination;
import br.com.alex.springAPI.domain.valueObjects.WorkoutId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetWorkoutDetailUseCase {
    private final IWorkoutRepository workoutRepository;

    public Pagination<WorkoutDetailOutput> execute(PageRequestApplication pagination) {

      Pagination<WorkoutDetail> workouts = this.workoutRepository.findAllWorkoutDetail(pagination.page(), pagination.size());


      Set<WorkoutId> ids = workouts.content().stream()
          .map(WorkoutDetail::workoutId)
          .collect(Collectors.toSet());

      Map<WorkoutId, Set<Exercise>> Map_exercisesDomain = this.workoutRepository.findAllExerciseByWorkoutIds(ids);

      Map<WorkoutId, Set<ExerciseOutput>> Map_exercisesOutput = Map_exercisesDomain.entrySet().stream()
          .collect(
              Collectors.toMap(
                  Map.Entry::getKey,
                  entry -> entry.getValue().stream().map(ExerciseOutput::of).collect(Collectors.toSet())
              )
          );

      List<WorkoutDetailOutput> outputsWorkouts = workouts.content().stream()
          .map(row -> WorkoutDetailOutput.builder()
              .workoutName(row.workoutName())
              .objective(row.objective())
              .studentName(row.studentName())
              .exercises(Map_exercisesOutput.getOrDefault(row.workoutId(), new HashSet<>()))
              .build())
          .toList();


      Pagination<WorkoutDetailOutput> output = Pagination.<WorkoutDetailOutput>builder()
          .pageSize(workouts.pageSize())
          .totalElements(workouts.totalElements())
          .pageCurrent(workouts.pageCurrent())
          .totalPages(workouts.totalPages())
          .contents(outputsWorkouts)
          .build();



      return output;
    }
}
