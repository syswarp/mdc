package com.syswarp.views.buques;

import java.util.Optional;

import com.syswarp.data.entity.Buques;
import com.syswarp.data.service.BuquesService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import com.syswarp.views.main.MainView;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.converter.StringToIntegerConverter;

@Route(value = "buques", layout = MainView.class)
@PageTitle("Buques")
@CssImport("./styles/views/buques/buques-view.css")
public class BuquesView extends Div {

    private Grid<Buques> grid = new Grid<>(Buques.class, false);

    private TextField id;
    private TextField imo;
    private TextField flag;
    private TextField grt;
    private TextField nombre;
    private TextField eslora;
    private TextField manga;
    private TextField puntal;
    private TextField coef;
    private TextField coeffac;
    private TextField grtfac;

    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");

    private BeanValidationBinder<Buques> binder;

    private Buques buques;

    public BuquesView(@Autowired BuquesService buquesService) {
        setId("buques-view");
        // Create UI
        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setSizeFull();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);

        // Configure Grid
        grid.addColumn("id").setAutoWidth(true);
        grid.addColumn("imo").setAutoWidth(true);
        grid.addColumn("flag").setAutoWidth(true);
        grid.addColumn("grt").setAutoWidth(true);
        grid.addColumn("nombre").setAutoWidth(true);
        grid.addColumn("eslora").setAutoWidth(true);
        grid.addColumn("manga").setAutoWidth(true);
        grid.addColumn("puntal").setAutoWidth(true);
        grid.addColumn("coef").setAutoWidth(true);
        grid.addColumn("coeffac").setAutoWidth(true);
        grid.addColumn("grtfac").setAutoWidth(true);
        grid.setItems(query -> buquesService.list(
                PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)))
                .stream());
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.setHeightFull();

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                Optional<Buques> buquesFromBackend = buquesService.get(event.getValue().getId());
                // when a row is selected but the data is no longer available, refresh grid
                if (buquesFromBackend.isPresent()) {
                    populateForm(buquesFromBackend.get());
                } else {
                    refreshGrid();
                }
            } else {
                clearForm();
            }
        });

        // Configure Form
        binder = new BeanValidationBinder<>(Buques.class);

        // Bind fields. This where you'd define e.g. validation rules
        binder.forField(id).withConverter(new StringToIntegerConverter("Only numbers are allowed")).bind("id");
        binder.forField(grt).withConverter(new StringToIntegerConverter("Only numbers are allowed")).bind("grt");
        binder.forField(eslora).withConverter(new StringToIntegerConverter("Only numbers are allowed")).bind("eslora");
        binder.forField(manga).withConverter(new StringToIntegerConverter("Only numbers are allowed")).bind("manga");
        binder.forField(puntal).withConverter(new StringToIntegerConverter("Only numbers are allowed")).bind("puntal");
        binder.forField(coef).withConverter(new StringToIntegerConverter("Only numbers are allowed")).bind("coef");
        binder.forField(coeffac).withConverter(new StringToIntegerConverter("Only numbers are allowed"))
                .bind("coeffac");
        binder.forField(grtfac).withConverter(new StringToIntegerConverter("Only numbers are allowed")).bind("grtfac");

        binder.bindInstanceFields(this);

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.buques == null) {
                    this.buques = new Buques();
                }
                binder.writeBean(this.buques);

                buquesService.update(this.buques);
                clearForm();
                refreshGrid();
                Notification.show("Buques details stored.");
            } catch (ValidationException validationException) {
                Notification.show("An exception happened while trying to store the buques details.");
            }
        });

    }

    private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setId("editor-layout");

        Div editorDiv = new Div();
        editorDiv.setId("editor");
        editorLayoutDiv.add(editorDiv);

        FormLayout formLayout = new FormLayout();
        id = new TextField("Id");
        imo = new TextField("Imo");
        flag = new TextField("Flag");
        grt = new TextField("Grt");
        nombre = new TextField("Nombre");
        eslora = new TextField("Eslora");
        manga = new TextField("Manga");
        puntal = new TextField("Puntal");
        coef = new TextField("Coef");
        coeffac = new TextField("Coeffac");
        grtfac = new TextField("Grtfac");
        Component[] fields = new Component[]{id, imo, flag, grt, nombre, eslora, manga, puntal, coef, coeffac, grtfac};

        for (Component field : fields) {
            ((HasStyle) field).addClassName("full-width");
        }
        formLayout.add(fields);
        editorDiv.add(formLayout);
        createButtonLayout(editorLayoutDiv);

        splitLayout.addToSecondary(editorLayoutDiv);
    }

    private void createButtonLayout(Div editorLayoutDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setId("button-layout");
        buttonLayout.setWidthFull();
        buttonLayout.setSpacing(true);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save, cancel);
        editorLayoutDiv.add(buttonLayout);
    }

    private void createGridLayout(SplitLayout splitLayout) {
        Div wrapper = new Div();
        wrapper.setId("grid-wrapper");
        wrapper.setWidthFull();
        splitLayout.addToPrimary(wrapper);
        wrapper.add(grid);
    }

    private void refreshGrid() {
        grid.select(null);
        grid.getLazyDataView().refreshAll();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(Buques value) {
        this.buques = value;
        binder.readBean(this.buques);

    }
}
