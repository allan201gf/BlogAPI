package br.com.allangf.BlogAPI.rest.Service;

import br.com.allangf.BlogAPI.rest.dto.EmailDTO;

public interface EmailService {

    void send(String title, String body);

    void addEmailList(EmailDTO emailDTO);

    void removeEmailList(EmailDTO emailDTO);

}
