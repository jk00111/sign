package iit.sign.api.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Cancel {

    private final long id;

    private final long requesterId;

}
