 public final class ParticipantFactory {

    private ParticipantFactory() {

    }

    public enum ParticipantType {
        Consumer, Distributor
    }

     /**
      *
      * @param participantType the type of the participant
      * @return returns a consumer or a distributor depending on the participantType
      */
    public static Participant createParticipant(final ParticipantType participantType) {
        switch (participantType) {
            case Consumer: return new Consumer();
            case Distributor: return new Distributor();
        }
        return null;
    }
}

