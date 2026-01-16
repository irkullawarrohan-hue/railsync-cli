package CNFM.TICKT.Services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import CNFM.TICKT.entities.Train;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

    public class TrainService {

        private List<Train> trainList;
        private ObjectMapper objectMapper = new ObjectMapper();
        private static final String TRAIN_DB_PATH = "app/src/main/resources/Localdb/Train.json";

        public TrainService() throws IOException {
            InputStream is = getClass()
                    .getClassLoader()
                    .getResourceAsStream("Localdb/Train.json");

            if (is == null) {
                throw new FileNotFoundException("Train.json not found in resources/Localdb");
            }

            trainList = objectMapper.readValue(
                    is,
                    new TypeReference<List<Train>>() {}
            );
        }

        public List<Train> searchTrains(String source, String destination) {
            return trainList.stream().filter(train -> validTrain(train, source, destination)).collect(Collectors.toList());
        }

        public void addTrain(Train newTrain) {
            Optional<Train> existingTrain = trainList.stream()
                    .filter(train -> train.getTrainId().equalsIgnoreCase(newTrain.getTrainId()))
                    .findFirst();

            if (existingTrain.isPresent()) {
                updateTrain(newTrain);
            } else {
                trainList.add(newTrain);
                saveTrainListToFile();
            }
        }

        public void updateTrain(Train updatedTrain) {
            OptionalInt index = IntStream.range(0, trainList.size())
                    .filter(i -> trainList.get(i).getTrainId().equalsIgnoreCase(updatedTrain.getTrainId()))
                    .findFirst();

            if (index.isPresent()) {
                trainList.set(index.getAsInt(), updatedTrain);
                saveTrainListToFile();
            } else {
                addTrain(updatedTrain);
            }
        }

        private void saveTrainListToFile() {
            try {
                objectMapper.writeValue(new File(TRAIN_DB_PATH), trainList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private boolean validTrain(Train train, String source, String destination) {

            Map<String, String> stationsMap = train.getStations();
            if (stationsMap == null) {
                return false;
            }
            List<String> stationOrder = new ArrayList<>(stationsMap.keySet());

            int sourceIndex = stationOrder.indexOf(source);
            int destinationIndex = stationOrder.indexOf(destination);

            return sourceIndex != -1
                    && destinationIndex != -1
                    && sourceIndex < destinationIndex;
        }

    }
