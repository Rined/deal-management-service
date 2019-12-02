package com.rined.deal.mail.services;

import com.rined.deal.mail.exception.TransformationNotFound;
import com.rined.deal.mail.model.DestinationDto;
import com.rined.deal.mail.model.DealTransformation;
import com.rined.deal.mail.properties.MailProps;
import com.rined.deal.mail.repositories.DealTransformRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DealMailTransformerImpl implements DealMailTransformer {
    private final MailProps mailProps;
    private final MailCreatorService creator;
    private final DealTransformRepository repository;

    @Override
    public SimpleMailMessage transform(DestinationDto destination) {
        String action = destination.getAction();
        Optional<DealTransformation> actionOptional = repository.findByAction(action);
        DealTransformation dealTransformation = actionOptional.orElseThrow(
                () -> new TransformationNotFound("Transformation for action %s not found", action)
        );
        return creator.create(
                destination.getEmailAddress(),
                mailProps.getAddress(),
                dealTransformation.getTitle(),
                String.format(dealTransformation.getTemplate(), destination.getUserName())
        );
    }
}
